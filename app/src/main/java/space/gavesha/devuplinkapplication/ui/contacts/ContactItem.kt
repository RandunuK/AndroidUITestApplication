package space.gavesha.devuplinkapplication.ui.contacts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import space.gavesha.devuplinkapplication.R
import space.gavesha.devuplinkapplication.doamin.model.Contact
import space.gavesha.devuplinkapplication.ui.theme.AppTheme
import space.gavesha.devuplinkapplication.ui.theme.DevUpLinkApplicationTheme
import space.gavesha.devuplinkapplication.ui.theme.Spacing
import space.gavesha.devuplinkapplication.ui.theme.getScaledFontSize


@Composable
internal fun ContactItem(
    contact: Contact,
    modifier: Modifier = Modifier,
    onIntent: (ContactIntent) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = AppTheme.spacing.cardElevation
        ),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.background,
            contentColor = AppTheme.colors.primaryText
        )
    ) {
        Column(
            modifier = Modifier.padding(top = AppTheme.spacing.cardPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DataRow(
                label = stringResource(R.string.first_name),
                value = contact.firstName, valueFontWeight = FontWeight.SemiBold
            )
            DataRow(
                label = stringResource(R.string.last_name),
                value = contact.lastName, valueFontWeight = FontWeight.SemiBold
            )
            DataRow(
                label = stringResource(R.string.phone_number),
                value = contact.phoneNumber
            )
            DataRow(
                label = stringResource(R.string.company_name),
                value = contact.companyName
            )
            DataRow(
                label = stringResource(R.string.created_at),
                value = contact.createdAt
            )
            HorizontalDivider(
                modifier = Modifier
                    .padding(horizontal = AppTheme.spacing.cardPadding),
                color = AppTheme.colors.outline,
                thickness = 1.dp
            )
            Row(
                modifier = Modifier
                    //.widthIn(max = 500.dp)
                    .fillMaxWidth()
                    //.align(Alignment.End)
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ActionButton(
                    icon = Icons.AutoMirrored.Outlined.Message,
                    text = stringResource(R.string.message),
                    modifier = Modifier.weight(1f),
                    onClick = { onIntent(ContactIntent.MessageClicked(contact)) }
                )
                ActionButton(
                    icon = Icons.Outlined.Call,
                    text = stringResource(R.string.call),
                    modifier = Modifier.weight(0.8f),
                    onClick = { onIntent(ContactIntent.CallClicked(contact)) }
                )
                ActionButton(
                    icon = Icons.Outlined.Edit,
                    text = stringResource(R.string.edit),
                    modifier = Modifier.weight(0.8f),
                    onClick = { onIntent(ContactIntent.EditClicked(contact)) }
                )
                ActionButton(
                    icon = Icons.Outlined.Delete,
                    text = stringResource(R.string.delete),
                    modifier = Modifier.weight(0.9f),
                    contentColor = AppTheme.colors.onDanger,
                    onClick = { onIntent(ContactIntent.DeleteClicked(contact)) },
                )
            }
        }
    }
}

@Composable
private fun ActionButton(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
    contentColor: Color = LocalContentColor.current,
    onClick: () -> Unit
) {
    val fontSize = getScaledFontSize(AppTheme.textSize.cardTextSize)
    TextButton(
        onClick = onClick,
        modifier = modifier.padding(vertical = 4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            modifier = Modifier.size(AppTheme.spacing.cardIconSize),
            tint = contentColor
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            fontSize = fontSize,
            text = text,
            style = TextStyle(color = contentColor),
            softWrap = false,
            maxLines = 1,
            overflow = TextOverflow.Visible,
        )
    }
}

// valueTextStyle
@Composable
private fun DataRow(label: String, value: String, valueFontWeight: FontWeight = FontWeight.Normal) {
    val fontSize = getScaledFontSize(AppTheme.textSize.cardTextSize)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = AppTheme.spacing.cardPadding, end = AppTheme.spacing.cardPadding, bottom = 8.dp)
    ) {
        Text(modifier = Modifier.wrapContentWidth(), text = label, fontSize = fontSize)
        Text(
            modifier = Modifier.fillMaxWidth(), text = value, textAlign = TextAlign.End,
            fontWeight = valueFontWeight, fontSize = fontSize
        )
    }
}

@Preview(showBackground = true, device = Devices.PHONE)
@PreviewLightDark
@Composable
fun ContactItemPreview() {
    val contact = Contact(
        "1",
        "John",
        "Doe",
        "+1 702-544-1963",
        "ABC Inc",
        "December 6,2024, 2:14 AM"
    )
    val onIntent: (ContactIntent) -> Unit = {}
    DevUpLinkApplicationTheme {
        ContactItem(contact = contact, onIntent = onIntent)
    }
}