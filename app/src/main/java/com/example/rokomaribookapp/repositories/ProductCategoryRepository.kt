package com.example.rokomaribookapp.repositories

import com.example.rokomaribookapp.db.AppDatabase
import com.example.rokomaribookapp.models.ProductCategory
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductCategoryRepository @Inject constructor(
    private val db: AppDatabase
) {
    suspend fun insert(productCategory: ProductCategory) = withContext(Dispatchers.IO) {
        db.productCategoryDao().insert(productCategory)
    }

}
