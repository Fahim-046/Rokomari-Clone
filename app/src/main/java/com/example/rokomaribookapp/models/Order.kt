package com.example.rokomaribookapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true)
    val orderId: Long = 0,
    val customerName: String,
    val customerEmail: String,
    var paymentMethod: String,
    val totalPrice: Int,
    val userId: String
)
