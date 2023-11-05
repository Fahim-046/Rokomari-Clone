package com.example.rokomaribookapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val bookName: String,
    val authorName: String,
    val price: Int,
    val copies: Long,
    val category: String,
    val releaseDate: Int,
    val description: String? = null,
    val inStock:Boolean = true
)
