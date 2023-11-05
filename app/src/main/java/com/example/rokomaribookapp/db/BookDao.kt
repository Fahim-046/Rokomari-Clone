package com.example.rokomaribookapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rokomaribookapp.models.Book

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(book: Book): Long

    @Query("SELECT COUNT(*) FROM books")
    fun count(): Long

    @Query("SELECT * FROM books WHERE category=:category")
    fun getBooks(category: String): List<Book>
}
