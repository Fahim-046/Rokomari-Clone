package com.example.rokomaribookapp.utils.extentions

import android.util.Log
import com.example.rokomaribookapp.db.AppDatabase
import com.example.rokomaribookapp.models.Author
import com.example.rokomaribookapp.models.Book
import com.example.rokomaribookapp.models.Categories
import com.example.rokomaribookapp.models.ProductCategory
import com.example.rokomaribookapp.models.Products
import kotlin.random.Random
import kotlin.random.nextInt

object MockDataGenerator {
    suspend fun generate(db: AppDatabase) {
        // ----------------------------------------------------------------
        // Author
        // ----------------------------------------------------------------
        Log.d("came", "came here")
        if (db.authorDao().count() == 0L) {
            for (index in 1L..100L) {
                db.authorDao().insertAuthor(
                    Author(
                        id = index,
                        name = "Author $index",
                        follower = index * 100
                    )
                )
            }
        }

        // ----------------------------------------------------------------
        // Krishi Books
        // ----------------------------------------------------------------

        if (db.bookDao().count() == 0L) {
            for (index in 1L..10L) {
                db.bookDao().insertBook(
                    Book(
                        id = index,
                        bookName = "Climate And Rice Cropping Systems In The Brahmaputra Basin",
                        authorName = "Haruhis Asad",
                        price = index.toInt() * 150,
                        copies = (index * 1000),
                        category = "Agriculture",
                        releaseDate = Random.nextInt(2013..2023),
                        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
                    )
                )
            }
            // ----------------------------------------------------------------
            // Drawing, Painting, Design & Photography Books
            // ----------------------------------------------------------------

            for (index in 11L..20L) {
                db.bookDao().insertBook(
                    Book(
                        id = index,
                        bookName = "Photography Dictionary",
                        authorName = "Sudipto Salam",
                        price = index.toInt() * 150,
                        copies = (index * 1000),
                        category = "Drawing, Painting, Design & Photography",
                        releaseDate = Random.nextInt(2013..2023),
                        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
                    )
                )
            }
            // ----------------------------------------------------------------
            // Bangladesh Related Books
            // ----------------------------------------------------------------

            for (index in 21L..30L) {
                db.bookDao().insertBook(
                    Book(
                        id = index,
                        bookName = "The Monsoon In Bangladesh",
                        authorName = "Monsoor Ali",
                        price = index.toInt() * 150,
                        copies = (index * 1000),
                        category = "Bangladesh",
                        releaseDate = Random.nextInt(2013..2023),
                        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
                    )
                )
            }
            // ----------------------------------------------------------------
            // Comic Books
            // ----------------------------------------------------------------

            for (index in 31L..40L) {
                db.bookDao().insertBook(
                    Book(
                        id = index,
                        bookName = "Noyon Sir",
                        authorName = "Mostaq Ahmed",
                        price = index.toInt() * 150,
                        copies = (index * 1000),
                        category = "Comics",
                        releaseDate = Random.nextInt(2013..2023),
                        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
                    )
                )
            }
        }

        // ----------------------------------------------------------------
        // Category
        // ----------------------------------------------------------------
        val catagories = listOf(
            "Agriculture",
            "Drawing, Painting, Design & Photography",
            "Bangladesh",
            "Comics"
        )
        if (db.categoryDao().count() == 0L) {
            for (index in 1L..4L) {
                db.categoryDao().insertCategory(
                    Categories(
                        id = index,
                        name = catagories[index.toInt() - 1]
                    )
                )
            }
        }
        // ----------------------------------------------------------------
        // Product Category
        // ----------------------------------------------------------------
        if (db.productCategoryDao().count() == 0L) {
            for (index in 1L..5L) {
                if (index == 1L) {
                    db.productCategoryDao().insert(
                        ProductCategory(
                            id = index,
                            name = "Electronics"
                        )
                    )
                }
                if (index == 2L) {
                    db.productCategoryDao().insert(
                        ProductCategory(
                            id = index,
                            name = "Kitchen Appliances",
                            parentId = 1
                        )
                    )
                }
                if (index == 3L) {
                    db.productCategoryDao().insert(
                        ProductCategory(
                            id = index,
                            name = "Irons",
                            parentId = 1
                        )
                    )
                }
                if (index == 4L) {
                    db.productCategoryDao().insert(
                        ProductCategory(
                            id = index,
                            name = "Smart Watch",
                            parentId = 1
                        )
                    )
                }
                if (index == 5L) {
                    db.productCategoryDao().insert(
                        ProductCategory(
                            id = index,
                            name = "Rice Cooker",
                            parentId = 1
                        )
                    )
                }
            }
        }

        // ----------------------------------------------------------------
        // Products
        // ----------------------------------------------------------------
        if (db.productDao().count() == 0L) {
            for (index in 1L..10L) {
                db.productDao().insert(
                    Products(
                        name = "Panasonic Mixer Grinder $index",
                        brand = "Panasonic",
                        price = 16450,
                        categoryId = 2L,
                        model = "1LFQ6X"
                    )
                )
            }
            for (index in 11L..20L) {
                db.productDao().insert(
                    Products(
                        name = "Vigo Iron Model $index",
                        brand = "Vigo",
                        price = 1450,
                        categoryId = 3L,
                        model = "2LFQ6X"
                    )
                )
            }
            for (index in 21L..30L) {
                db.productDao().insert(
                    Products(
                        name = "Amazfit Bip $index",
                        brand = "Amazfit",
                        price = 4899,
                        categoryId = 4L,
                        model = "3LFQ6X"
                    )
                )
            }
            for (index in 31L..40L) {
                db.productDao().insert(
                    Products(
                        name = "Prestige Double inner pot $index",
                        brand = "Prestige",
                        price = 3360,
                        categoryId = 5L,
                        model = "4LFQ6X"
                    )
                )
            }
        }
    }
}
