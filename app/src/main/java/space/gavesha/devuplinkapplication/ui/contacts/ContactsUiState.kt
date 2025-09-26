package space.gavesha.devuplinkapplication.ui.contacts

import space.gavesha.devuplinkapplication.doamin.model.Contact

data class ContactsUiState(
    val selectedTab: Int = 0,
    val contactsTabContent: ContactsTabContentUiState = ContactsTabContentUiState()
)

// Result<> ???
data class ContactsTabContentUiState(
    val contacts: List<Contact> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null,
)