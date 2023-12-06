package com.esms.services

import android.content.Context
import android.provider.ContactsContract
import com.esms.models.PhoneContact

fun readContacts(context: Context): List<PhoneContact> {
    val contacts = mutableListOf<PhoneContact>()

    val contactsCursor = context.contentResolver.query(
        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
        arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.PHOTO_URI),
        null,
        null,
        null
    )

    contactsCursor?.use {
        val indexName = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        val indexNumber = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
        val indexPhoto = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI)

        while (it.moveToNext()) {
            val photoUri = it.getString(indexPhoto)
            println(photoUri)
            contacts.add(
                PhoneContact(
                    pfp = photoUri,
                    name = it.getString(indexName),
                    number = it.getString(indexNumber)
                )
            )
        }
    }

    return contacts
}