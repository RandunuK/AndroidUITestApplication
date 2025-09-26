package space.gavesha.devuplinkapplication.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val White = Color(0xFFFFFFFF) // active_color
val White200 = Color(0xFFBBBBBB) // inactive_color, list text color
val White300 = Color(0xFFECECEC) // v
val White800 = Color(0xFFFAFAFA)

val Black300 = Color(0xFF333333) // list text color
val Black250 = Color(0xFF454545) // fab - dark color #454545
val Black400 = Color(0xFF2A2A2A) // active_color
val Black500 = Color(0xFF111111) // v

val DMRed = Color(0xFFFF7169)
val LMRed = Color(0xFFA5241D)
val DMSelectedBackground = Color(0xFF3B3B3B)
val LMSelectedBackground = Color(0xFFE7E7E7)
val LMOutline = Color(0x2A2A2A29)
val DMOutline = Color(0xFF333333)

@Immutable
data class AppColors(
    val background: Color = Color.Unspecified,
    val primary: Color = Color.Unspecified,
    val primaryContainer: Color = Color.Unspecified,
    val onPrimaryContainer: Color = Color.Unspecified,
    val primaryText: Color = Color.Unspecified,
    val secondaryText: Color = Color.Unspecified,
    val tabBackground: Color = Color.Unspecified,
    val onDanger: Color = Color.Unspecified,
    val onSurfaceVariant: Color = Color.Unspecified, // inactive text color
    val optionBarSurface: Color = Color.Unspecified,
    val bottomNavSurface: Color = Color.Unspecified,
    val outline: Color = Color.Unspecified,
    val optionIconTint: Color = Color.Unspecified,
    val selectedNavItemColor: Color = Color.Unspecified,
    val selectedNavItemBackground: Color = Color.Unspecified,
)

val LocalAppColors = staticCompositionLocalOf { AppColors() }

val LightAppColors = AppColors(
    background = White,
    primary = Black300,
    primaryText = Black500,
    onDanger = LMRed,
    onSurfaceVariant = Black250,
    optionBarSurface = White300,
    tabBackground = White800,
    bottomNavSurface = White800,
    outline = LMOutline,
    optionIconTint = Black500,
    primaryContainer = Black300,
    onPrimaryContainer = White,
    selectedNavItemColor = Black300,
    selectedNavItemBackground = LMSelectedBackground
)

val DarkAppColors = AppColors(
    background = Black500,
    primary = Black250,
    primaryText = White,
    onDanger = DMRed,
    onSurfaceVariant = White200,
    optionBarSurface = Black400,
    tabBackground = Black400,
    bottomNavSurface = Black400,
    outline = DMOutline,
    optionIconTint = White,
    primaryContainer = Black250,
    onPrimaryContainer = White,
    selectedNavItemColor = White,
    selectedNavItemBackground = DMSelectedBackground
)