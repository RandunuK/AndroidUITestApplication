package space.gavesha.devuplinkapplication.ui.common.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import space.gavesha.devuplinkapplication.R
import space.gavesha.devuplinkapplication.ui.theme.AppTheme

@Composable
internal fun TitleSearchBar(
    state: TitleSearchBarUiState,
    modifier: Modifier = Modifier,
    onIntent: (TitleSearchBarIntent) -> Unit = {}
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp),
        color = AppTheme.colors.background,
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Back button when searching
            if (state.isSearchActive) {
                IconButton(onClick = { onIntent(TitleSearchBarIntent.SearchClosed) }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.cd_back)
                    )
                }
            } else {
                Spacer(modifier = Modifier.width(16.dp))
            }

            // Title or Search Field
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            ) {
                if (state.isSearchActive) {
                    BasicTextField(
                        value = state.searchQuery,
                        onValueChange = { onIntent(TitleSearchBarIntent.QueryChanged(it)) },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = MaterialTheme.typography.titleLarge.copy(
                            color = AppTheme.colors.primaryText
                        ),
                        singleLine = true,
                        decorationBox = { innerTextField ->
                            if (state.searchQuery.isEmpty()) {
                                Text(
                                    text = stringResource(R.string.search),
                                    style = MaterialTheme.typography.titleLarge,
                                    color = AppTheme.colors.onSurfaceVariant
                                )
                            }
                            innerTextField()
                        }
                    )
                } else {
                    Text(
                        text = state.title,
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            // Action buttons
            if (state.isSearchActive) {
                if (state.searchQuery.isNotEmpty()) {
                    IconButton(onClick = { onIntent(TitleSearchBarIntent.QueryChanged("")) }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(R.string.clear)
                        )
                    }
                }
            } else {
                IconButton(onClick = { { onIntent(TitleSearchBarIntent.SearchClicked) } }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.cd_search)
                    )
                }
            }
        }
    }
}