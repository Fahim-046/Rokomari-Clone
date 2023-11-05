package com.example.rokomaribookapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rokomaribookapp.models.Author

@Dao
interface AuthorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAuthor(author: Author): Long

    @Query("SELECT COUNT(*) FROM authors")
    fun count(): Long

    @Query("SELECT * FROM authors")
    fun getAll():List<Author>
}
