package space.gavesha.devuplinkapplication.ui.common.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import space.gavesha.devuplinkapplication.R
import space.gavesha.devuplinkapplication.ui.theme.AppTheme

@Composable
internal fun TitleSearchBar(
    state: TitleSearchBarUiState,
    modifier: Modifier = Modifier,
    onIntent: (TitleSearchBarIntent) -> Unit = {}
) {
    val topAppBarHeight = TopAppBarDefaults.TopAppBarExpandedHeight
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.statusBars),
        color = AppTheme.colors.background,
    ) {
        Row(
            modifier = Modifier
                .height(topAppBarHeight)
                .padding(end = AppTheme.spacing.dp8),
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
                Spacer(modifier = Modifier.width(AppTheme.spacing.dp16))
            }

            // Title or Search Field
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = AppTheme.spacing.dp8)
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
                IconButton(onClick = { onIntent(TitleSearchBarIntent.SearchClicked) }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.cd_search)
                    )
                }
            }
        }
    }
}