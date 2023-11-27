package com.example.rokomaribookapp.repositories

import com.example.rokomaribookapp.db.AppDatabase
import com.example.rokomaribookapp.models.User
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository @Inject constructor(
    private val db: AppDatabase
) {
    suspend fun insert(user: User) = withContext(Dispatchers.IO) {
        db.userDao().insert(user)
    }
}
