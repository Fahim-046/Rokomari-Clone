package com.example.rokomaribookapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rokomaribookapp.models.Author
import com.example.rokomaribookapp.models.Book
import com.example.rokomaribookapp.models.Cart
import com.example.rokomaribookapp.models.Categories
import com.example.rokomaribookapp.models.Order
import com.example.rokomaribookapp.models.ProductCategory
import com.example.rokomaribookapp.models.Products
import com.example.rokomaribookapp.models.User
import com.example.rokomaribookapp.utils.extentions.DataConverter

@TypeConverters(DataConverter::class)
@Database(
    entities = [
        Book::class,
        Author::class,
        Categories::class,
        Cart::class,
        Products::class,
        ProductCategory::class,
        User::class,
        Order::class
    ],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao
    abstract fun authorDao(): AuthorDao
    abstract fun categoryDao(): CategoryDao
    abstract fun cartDao(): CartDao
    abstract fun productDao(): ProductDao
    abstract fun productCategoryDao(): ProductCategoryDao
    abstract fun userDao(): UserDao
    abstract fun orderDao(): OrderDao

    companion object {
        operator fun invoke(context: Context) = buildDatabase(context)

        private fun buildDatabase(context: Context): AppDatabase {
            val databaseBuilder = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "MainDatabase.db"
            )
            return databaseBuilder.build()
        }
    }
}
