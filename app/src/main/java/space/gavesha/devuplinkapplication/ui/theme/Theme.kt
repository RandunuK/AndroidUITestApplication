package space.gavesha.devuplinkapplication.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext


private val LightColorScheme = lightColorScheme(
    background = White,
    primary = Black300,
)

private val DarkColorScheme = darkColorScheme(
    background = Black500,
    primary = Black250,
)

@Composable
fun DevUpLinkApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val customAppColors = if (darkTheme) DarkAppColors else LightAppColors

    CompositionLocalProvider(
        LocalAppColors provides customAppColors
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}

object AppTheme {
    val colors: AppColors
        @Composable
        get() = LocalAppColors.current
    val textSize: TextSize
        @Composable
        get() = LocalTextSize.current

    val spacing: Spacing
        @Composable
        @ReadOnlyComposable
        get() = LocalSpacing.current
}
