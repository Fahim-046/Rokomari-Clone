package com.example.rokomaribookapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "authors")
data class Author(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val follower: Long

)
