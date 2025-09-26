package space.gavesha.devuplinkapplication.ui.common.optionbar

interface OptionBarIntent {
    data object SortClicked : OptionBarIntent
    data object FilterClicked : OptionBarIntent
    data object ToggleClicked : OptionBarIntent
}