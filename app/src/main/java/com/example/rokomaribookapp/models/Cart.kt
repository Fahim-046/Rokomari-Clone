package com.example.rokomaribookapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class Cart(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cartId")
    val id: Long = 0,
    val itemId: Long,
    val itemName: String,
    var itemPrice: Int,
    var itemAmount: Int = 1,
    var isSelected: Boolean = true,
    var userId: String? = null
)
