package space.gavesha.devuplinkapplication.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Sort
import androidx.compose.material.icons.automirrored.outlined.ViewList
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import space.gavesha.devuplinkapplication.R
import space.gavesha.devuplinkapplication.ui.common.optionbar.OptionBarIntent
import space.gavesha.devuplinkapplication.ui.theme.AppTheme
import space.gavesha.devuplinkapplication.ui.theme.DevUpLinkApplicationTheme

@Composable
internal fun OptionBar(
    modifier: Modifier = Modifier,
    onIntent: (OptionBarIntent) -> Unit = {}
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = AppTheme.colors.optionBarSurface
    ) {
        Row(
            modifier = Modifier
                .padding(
                    horizontal = AppTheme.spacing.optionBarHorizontalPadding,
                    vertical = AppTheme.spacing.optionBarVerticalPadding
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Sort by
            OptionButton(
                icon = Icons.AutoMirrored.Outlined.Sort,
                text = stringResource(R.string.sort_by),
                onClick = { onIntent(OptionBarIntent.SortClicked) }
            )

            // Filters
            OptionButton(
                icon = Icons.Outlined.FilterAlt,
                text = stringResource(R.string.filters),
                onClick = { onIntent(OptionBarIntent.FilterClicked) }
            )

            // Toggle
            OptionButton(
                icon = Icons.AutoMirrored.Outlined.ViewList,
                text = stringResource(R.string.toggle),
                onClick = { onIntent(OptionBarIntent.ToggleClicked) }
            )
        }
    }
}

@Composable
private fun OptionButton(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
            .background(
                AppTheme.colors.background,
                shape = RoundedCornerShape(percent = 50)
            )
            .border(
                width = 1.5.dp,
                color = AppTheme.colors.outline,
                shape = RoundedCornerShape(percent = 50)
            )
            .padding(horizontal = 4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            modifier = Modifier.size(AppTheme.spacing.optionIconSize),
            tint = AppTheme.colors.optionIconTint
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text, color = AppTheme.colors.primaryText)
    }
}

@Preview(showBackground = true)
@PreviewLightDark
@Composable
fun OptionBarPreview() {
    DevUpLinkApplicationTheme {
        OptionBar(onIntent = {})
    }
}