package com.example.rokomaribookapp.repositories

import com.example.rokomaribookapp.db.AppDatabase
import com.example.rokomaribookapp.models.Order
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OrderRepository @Inject constructor(
    private val db: AppDatabase
) {
    suspend fun insertOrderWithCartItems(order: Order) =
        withContext(Dispatchers.IO) {
            db.orderDao().insertOrderWithCartItems(order)
        }

    suspend fun getOrders(userId: String): List<Order> = withContext(Dispatchers.IO) {
        db.orderDao().getOrders(userId)
    }
}
