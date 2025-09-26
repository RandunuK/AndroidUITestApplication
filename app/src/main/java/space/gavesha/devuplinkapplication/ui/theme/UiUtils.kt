package space.gavesha.devuplinkapplication.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun getContainerWidthDp(): Dp {
    val containerWidth = LocalWindowInfo.current.containerSize.width
    return with(LocalDensity.current) { containerWidth.toDp() }
}

@Composable
fun getScaledFontSize(
    baseSizeSp: TextUnit,
    maxScale: Float = 1.3f
): TextUnit {
    val widthDp = getContainerWidthDp()
    val fontScale = LocalDensity.current.fontScale

    // Adjust based on container width
    val baseAdjustment = when {
        widthDp < 360.dp -> 0.9f
        widthDp < 400.dp -> 0.95f
        else -> 1.0f
    }

    val limitedScale = fontScale.coerceAtMost(maxScale)
    return (baseSizeSp.value * baseAdjustment * limitedScale / fontScale).sp
}

