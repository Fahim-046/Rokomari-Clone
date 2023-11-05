package com.example.rokomaribookapp.repositories

import androidx.lifecycle.LiveData
import com.example.rokomaribookapp.db.AppDatabase
import com.example.rokomaribookapp.models.Author
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthorRepository @Inject constructor(
    private val db: AppDatabase
) {
    suspend fun getAll(): List<Author> = withContext(Dispatchers.IO) {
        db.authorDao().getAll()
    }
}
