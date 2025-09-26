package space.gavesha.devuplinkapplication.ui.contacts

import space.gavesha.devuplinkapplication.doamin.model.Contact

sealed interface ContactIntent {
    data class MessageClicked(val contact: Contact) : ContactIntent
    data class CallClicked(val contact: Contact) : ContactIntent
    data class EditClicked(val contact: Contact) : ContactIntent
    data class DeleteClicked(val contact: Contact) : ContactIntent
}
