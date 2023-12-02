package com.example.rokomaribookapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.rokomaribookapp.models.Cart

@Dao
interface CartDao {
    @Upsert
    fun insert(cart: Cart)

    @Upsert
    fun insertAll(cart: List<Cart>)

    @Query("SELECT * FROM cart WHERE userId=:id and isSelected=1")
    fun getAllItem(id: String?): List<Cart>

    @Query("SELECT COUNT(*) FROM cart WHERE itemId=:id")
    fun count(id: Long): Long

    @Query("UPDATE cart SET isSelected=:value")
    fun changeAll(value: Boolean)

    @Update
    fun update(cart: Cart)

    @Query("SELECT SUM(itemPrice) FROM cart WHERE isSelected=1")
    fun getTotalPrice(): Long

    @Query("SELECT COUNT(isSelected) FROM cart WHERE isSelected=1")
    fun getItemCount(): Long

    @Delete
    fun deleteItem(cart: Cart)

    @Query("DELETE FROM cart WHERE userId=:id")
    fun deleteAll(id: String)
}
