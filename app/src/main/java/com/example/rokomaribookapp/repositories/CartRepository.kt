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

    suspend fun getAll(): List<Cart> = withContext(Dispatchers.IO) {
        db.cartDao().getAllItem()
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

    suspend fun getItemCount(): Long = withContext(Dispatchers.IO){
        db.cartDao().getItemCount()
    }

    suspend fun deleteItem(cart: Cart) = withContext(Dispatchers.IO){
        db.cartDao().deleteItem(cart)
    }
}
