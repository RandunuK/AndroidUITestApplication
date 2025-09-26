package space.gavesha.devuplinkapplication.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

object Spacing {
    val dp2 = 2.dp
    val dp4 = 4.dp
    val dp6 = 6.dp
    val dp8 = 8.dp
    val dp12 = 12.dp
    val dp16 = 16.dp
    val dp20 = 20.dp
    val dp24 = 24.dp
    val dp32 = 32.dp
    val dp40 = 40.dp

    val screenPadding = dp16
    val optionBarHorizontalPadding = dp16
    val optionBarVerticalPadding = dp12
    val optionIconSize = dp20
    val cardIconSize = dp20

    val listItemBottomPadding = dp2
    val cardElevation = dp2
    val cardPadding = dp20
}

val LocalSpacing = staticCompositionLocalOf { Spacing }