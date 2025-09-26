package space.gavesha.devuplinkapplication.ui.common.search

interface TitleSearchBarIntent {
    data class QueryChanged(val query: String) : TitleSearchBarIntent
    data object SearchClicked : TitleSearchBarIntent
    data object SearchClosed : TitleSearchBarIntent
}