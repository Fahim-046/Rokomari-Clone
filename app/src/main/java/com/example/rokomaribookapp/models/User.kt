package com.example.rokomaribookapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.URL

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val uid: String,
    val userName: String,
    val phoneNumber: String?,
    val photoURL: String
)
