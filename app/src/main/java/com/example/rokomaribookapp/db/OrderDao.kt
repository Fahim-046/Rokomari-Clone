package com.example.rokomaribookapp.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.rokomaribookapp.models.Order

@Dao
interface OrderDao {
    @Upsert
    fun insertOrderWithCartItems(order: Order)

    @Query("SELECT * FROM orders WHERE userId=:userId")
    fun getOrders(userId: String): List<Order>
}
