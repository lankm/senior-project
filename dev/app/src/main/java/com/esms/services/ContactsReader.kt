package com.esms.services

import android.content.Context
import android.provider.ContactsContract
import com.esms.R
import com.esms.models.PhoneContact
import com.esms.models.SMSMessage

public fun readContacts(context: Context): List<PhoneContact> {
    val contacts = mutableListOf<PhoneContact>()

    val contactsUri = ContactsContract.Contacts.CONTENT_URI
    val projection = arrayOf(ContactsContract.Contacts.DISPLAY_NAME)
    val cursor = context.contentResolver.query(
        contactsUri,
        projection,
        null,
        null,
        null
    )

    cursor?.use {
        val indexName = it.getColumnIndex("display_name")

        while (it.moveToNext()) {
            contacts.add(
                PhoneContact(
                    pfp = R.drawable.ic_launcher_background,    //TODO
                    name = it.getString(indexName),
                    number = "999-999-9999" //TODO
                )
            )
        }
    }

    return contacts
}