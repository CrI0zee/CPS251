package com.example.contacts

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class ContactRepository(application: Application) {
    val searchResults = MutableLiveData<List<Contact>>()
    private var contactDao: ContactDao?
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val allContacts: LiveData<List<Contact>>?
    val allContactsDESC: LiveData<List<Contact>>?
    val allContactsASC: LiveData<List<Contact>>?

    init {
        val db: ContactRoomDatabase? =
            ContactRoomDatabase.getDatabase(application)
        contactDao = db?.contactDao()
        allContacts = contactDao?.getAllContacts()
        allContactsASC = contactDao?.getAllContactsASC()
        allContactsDESC = contactDao?.getAllContactsDESC()
    }

    fun insertContact(newcontact: Contact) {
        coroutineScope.launch(Dispatchers.IO) {
            asyncInsert(newcontact)
        }
    }

    private suspend fun asyncInsert(contact: Contact) {
        contactDao?.insertContact(contact)
    }

    fun deleteContact(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            asyncDelete(id)
        }
    }

    private suspend fun asyncDelete(id: Int) {
        contactDao?.deleteContact(id)
    }

    fun findContact(name: String) {
        coroutineScope.launch(Dispatchers.Main) {
            val foundContact: List<Contact>? = asyncFind(name).await()
        }
    }

    private suspend fun asyncFind(name: String): Deferred<List<Contact>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async contactDao?.findContact(name)
        }

    fun getAllContactsASC() {
        coroutineScope.launch(Dispatchers.Main) {
            val allContactsASC: LiveData<List<Contact>>? = asyncASC().await()
        }
    }
    private suspend fun asyncASC() : Deferred<LiveData<List<Contact>>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async contactDao?.getAllContactsASC()
        }

    fun getAllContactsDESC() {
        coroutineScope.launch(Dispatchers.Main) {
            val allContactsDESC: LiveData<List<Contact>>? = asyncDESC().await()
        }
    }
    private suspend fun asyncDESC() : Deferred<LiveData<List<Contact>>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async contactDao?.getAllContactsDESC()
        }
}