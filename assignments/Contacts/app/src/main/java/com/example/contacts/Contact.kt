package com.example.contacts
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
class Contact {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "contactId")
    var id: Int = 0

    @ColumnInfo(name = "contactName")
    var contactName: String? = null

    @ColumnInfo(name = "contactPhone")
    var contactPhone: String? = null

    constructor() {}

    constructor(id: Int, contactName: String, contactPhone: String) {
        this.id = id
        this.contactName = contactName
        this.contactPhone = contactPhone
    }

    constructor(contactName: String, contactPhone: String) {
        this.contactName = contactName
        this.contactPhone = contactPhone
    }


}