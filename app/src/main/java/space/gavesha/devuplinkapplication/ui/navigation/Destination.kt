package space.gavesha.devuplinkapplication.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Pending
import androidx.compose.material.icons.filled.Textsms
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Contacts
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.Pending
import androidx.compose.material.icons.outlined.Textsms
import androidx.compose.ui.graphics.vector.ImageVector
import space.gavesha.devuplinkapplication.R

enum class Destination(
    val route: String,
    @StringRes val labelRes: Int,
    val defaultIcon: ImageVector,
    val selectedIcon: ImageVector,
    @StringRes val contentDescriptionRes: Int
) {
    CONTACTS(
        "contacts",
        R.string.nav_contacts,
        Icons.Outlined.Contacts,
        Icons.Default.Contacts,
        R.string.cd_contacts
    ),
    MESSAGES(
        "messages",
        R.string.nav_messages,
        Icons.Outlined.Textsms,
        Icons.Filled.Textsms,
        R.string.cd_messages
    ),
    CALLING(
        "calling",
        R.string.nav_calling,
        Icons.Outlined.Call,
        Icons.Filled.Call,
        R.string.cd_calling
    ),
    NOTIFICATION(
        "notification",
        R.string.nav_notification,
        Icons.Outlined.NotificationsNone,
        Icons.Filled.Notifications,
        R.string.cd_notification
    ),
    MORE(
        "more",
        R.string.nav_more,
        Icons.Outlined.Pending,
        Icons.Default.Pending,
        R.string.cd_more
    )
}