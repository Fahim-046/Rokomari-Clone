package com.example.rokomaribookapp.models

import java.net.URL

data class User(
    val uid: String,
    val userName: String,
    val phoneNumber: String?,
    val photoURL: URL?
)
