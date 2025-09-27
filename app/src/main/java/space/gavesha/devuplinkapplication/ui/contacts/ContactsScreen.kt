package space.gavesha.devuplinkapplication.ui.contacts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import space.gavesha.devuplinkapplication.R
import space.gavesha.devuplinkapplication.data.DummyContacts
import space.gavesha.devuplinkapplication.ui.common.OptionBar
import space.gavesha.devuplinkapplication.ui.theme.AppTheme
import space.gavesha.devuplinkapplication.ui.theme.DevUpLinkApplicationTheme

@Composable
internal fun ContactScreen(modifier: Modifier = Modifier) {
    val viewModel: ContactViewModel = viewModel()

    LaunchedEffect(Unit) {
        viewModel.fetchContacts()
    }
    val contactsUiState = viewModel.uiState.collectAsState()
    ContactsScreenContent(contactsUiState.value, modifier)
}

@Composable
internal fun ContactsScreenContent(contactsUiState: ContactsUiState, modifier: Modifier = Modifier) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf(
        stringResource(R.string.contacts),
        stringResource(R.string.contact_groups)
    )
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SecondaryTabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = AppTheme.colors.background,
        ) {
            tabs.forEachIndexed { index, title ->
                val isSelected = selectedTabIndex == index
                val tabContentColor = if (isSelected) {
                    AppTheme.colors.primaryText
                } else {
                    AppTheme.colors.onSurfaceVariant
                }
                Tab(
                    selected = isSelected,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = title, color = tabContentColor) }
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when (selectedTabIndex) {
                0 -> ContactsTab(contactsUiState.contactsTabContent)
                1 -> ContactGroupsTab(modifier)
            }
        }
    }
}

@Composable
private fun ContactsTab(uiState: ContactsTabContentUiState) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.wrapContentHeight()) {
            OptionBar()
        }
        Row(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(bottom = AppTheme.spacing.contactListContentPadding),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(
                    items = uiState.contacts,
                    key = { it.id }
                ) { contact ->
                    ContactItem(
                        contact = contact,
                        onIntent = { /* handle intent */ }
                    )
                }
            }
        }
    }
}

@Composable
private fun ContactGroupsTab(modifier: Modifier = Modifier) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(stringResource(R.string.contact_groups))
    }
}

@Preview(showBackground = true)
@PreviewLightDark
@Composable
fun OptionBarPreview() {
    val contactsTabContentUiState = ContactsTabContentUiState(contacts = DummyContacts.contacts)
    val contactsUiState = ContactsUiState(contactsTabContent = contactsTabContentUiState)
    DevUpLinkApplicationTheme {
        ContactsScreenContent(contactsUiState)
    }
}