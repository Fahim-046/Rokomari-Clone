package com.example.rokomaribookapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rokomaribookapp.models.Products
import com.example.rokomaribookapp.models.ProductsWithCategory

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(products: Products): Long

    @Query("SELECT COUNT(*) FROM products")
    fun count(): Long

    @Query("SELECT * FROM product_categories WHERE parentId=:id")
    fun productList(id: Long): List<ProductsWithCategory>

    @Query("SELECT * FROM product_categories WHERE id=:id")
    fun subCategoryProductList(id: Long): ProductsWithCategory
}
