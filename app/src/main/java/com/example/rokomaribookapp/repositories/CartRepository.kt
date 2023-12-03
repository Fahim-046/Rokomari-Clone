package com.example.rokomaribookapp.repositories

import com.example.rokomaribookapp.db.AppDatabase
import com.example.rokomaribookapp.models.Cart
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartRepository @Inject constructor(
    private val db: AppDatabase
) {
    suspend fun insert(cart: Cart) = withContext(Dispatchers.IO) {
        db.cartDao().insert(cart)
    }

    suspend fun getAll(id: String?): List<Cart> = withContext(Dispatchers.IO) {
        db.cartDao().getAll(id)
    }
    suspend fun getAllItem(id: String?): List<Cart> = withContext(Dispatchers.IO) {
        db.cartDao().getAllItem(id)
    }

    suspend fun count(id: Long): Long = withContext(Dispatchers.IO) {
        db.cartDao().count(id)
    }

    suspend fun changeAll(value: Boolean) = withContext(Dispatchers.IO) {
        db.cartDao().changeAll(value)
    }

    suspend fun update(cart: Cart) = withContext(Dispatchers.IO) {
        db.cartDao().update(cart)
    }

    suspend fun getTotalPrice(): Long = withContext(Dispatchers.IO) {
        db.cartDao().getTotalPrice()
    }

    suspend fun getItemCount(): Long = withContext(Dispatchers.IO) {
        db.cartDao().getItemCount()
    }

    suspend fun deleteItem(cart: Cart) = withContext(Dispatchers.IO) {
        db.cartDao().deleteItem(cart)
    }

    suspend fun insertAll(carts: List<Cart>) = withContext(Dispatchers.IO) {
        db.cartDao().insertAll(carts)
    }

    suspend fun deleteAll(userId: String) = withContext(Dispatchers.IO) {
        db.cartDao().deleteAll(userId)
    }
}
