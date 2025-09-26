package space.gavesha.devuplinkapplication.ui.contacts

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import space.gavesha.devuplinkapplication.data.DummyContacts

class ContactViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ContactsUiState())
    val uiState: StateFlow<ContactsUiState> = _uiState.asStateFlow()

    fun fetchContacts() {
        val contacts = DummyContacts.contacts
        val contactsTabContentUiState = ContactsTabContentUiState(contacts = contacts, isLoading = false)
        _uiState.value = ContactsUiState(contactsTabContent = contactsTabContentUiState)
    }
}