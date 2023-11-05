package com.example.rokomaribookapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rokomaribookapp.models.Categories

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(categories: Categories): Long

    @Query("SELECT * FROM categories")
    fun getAll(): List<Categories>

    @Query("SELECT COUNT(*) FROM categories")
    fun count(): Long
}
