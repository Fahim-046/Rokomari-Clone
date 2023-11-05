package com.example.rokomaribookapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_categories")
data class ProductCategory(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    var parentId: Long? = null
)
