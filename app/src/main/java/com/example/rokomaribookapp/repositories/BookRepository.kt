package com.example.rokomaribookapp.repositories

import com.example.rokomaribookapp.db.AppDatabase
import com.example.rokomaribookapp.models.Book
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookRepository @Inject constructor(
    private val db: AppDatabase
) {
    suspend fun getBYCategory(category: String): List<Book> = withContext(Dispatchers.IO) {
        db.bookDao().getBooks(category)
    }
}
