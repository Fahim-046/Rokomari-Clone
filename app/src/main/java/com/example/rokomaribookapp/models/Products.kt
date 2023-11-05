package com.example.rokomaribookapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Products(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val brand: String,
    val price: Int,
    val categoryId: Long,
    var capacity: String? = null,
    val model: String,
    val company: String? = null
)
