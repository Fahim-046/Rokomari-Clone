package com.example.rokomaribookapp.repositories

import com.example.rokomaribookapp.db.AppDatabase
import javax.inject.Inject

class OrderRepository @Inject constructor(
    private val db: AppDatabase
)
