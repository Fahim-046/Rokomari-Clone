package com.example.rokomaribookapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rokomaribookapp.models.ProductCategory

@Dao
interface ProductCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(productCategory: ProductCategory)

    @Query("SELECT COUNT(*) FROM product_categories")
    fun count(): Long
}
