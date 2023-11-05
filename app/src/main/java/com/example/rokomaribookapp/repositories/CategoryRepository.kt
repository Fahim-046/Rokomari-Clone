package com.example.rokomaribookapp.repositories

import com.example.rokomaribookapp.db.AppDatabase
import com.example.rokomaribookapp.models.Categories
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryRepository @Inject constructor(
    private val db: AppDatabase
) {
    suspend fun getAll(): List<Categories> = withContext(Dispatchers.IO) {
        db.categoryDao().getAll()
    }
}
