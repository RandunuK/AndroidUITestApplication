package space.gavesha.devuplinkapplication.ui.common.search

data class TitleSearchBarUiState(
    val title: String,
    val isSearchActive: Boolean = false,
    val searchQuery: String = ""
)