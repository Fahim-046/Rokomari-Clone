package com.example.rokomaribookapp.repositories

import com.example.rokomaribookapp.db.AppDatabase
import com.example.rokomaribookapp.models.Products
import com.example.rokomaribookapp.models.ProductsWithCategory
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepository @Inject constructor(
    val db: AppDatabase
) {
    suspend fun insert(products: Products) = withContext(Dispatchers.IO) {
        db.productDao().insert(products)
    }

    suspend fun getList(id: Long): List<ProductsWithCategory> = withContext(Dispatchers.IO) {
        db.productDao().productList(id)
    }

    suspend fun getItem(id: Long): ProductsWithCategory = withContext(Dispatchers.IO) {
        db.productDao().subCategoryProductList(id)
    }
}
