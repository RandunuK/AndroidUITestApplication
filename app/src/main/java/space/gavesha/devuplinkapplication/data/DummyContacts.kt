package space.gavesha.devuplinkapplication.data

import space.gavesha.devuplinkapplication.doamin.model.Contact

object DummyContacts {
    val contact1 = Contact(
        "1",
        "Brooke",
        "Robinson",
        "+1 702-544-1963",
        "ABC Inc",
        "December 6,2024, 2:14 AM"
    )
    val contacts = mutableListOf(contact1)

    init {
        for (i in 2..10) {
            contacts.add(contact1.copy(id = i.toString()))
        }
    }
}