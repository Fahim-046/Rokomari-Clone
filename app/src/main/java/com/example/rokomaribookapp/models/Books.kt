package com.example.rokomaribookapp.models

data class Books(
    val id: Int,
    val bookName: String? = null,
    val bookImage: Int,
    val category: String? = null,
    val price: String? = null
)
