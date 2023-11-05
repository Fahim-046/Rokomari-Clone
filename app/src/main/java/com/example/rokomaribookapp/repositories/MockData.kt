package com.example.rokomaribookapp.repositories

import com.example.rokomaribookapp.models.Author
import com.example.rokomaribookapp.models.Book
import com.example.rokomaribookapp.models.Categories
import kotlin.random.Random
import kotlin.random.nextInt

object MockData {
    // ----------------------------------------------------------------
    // Author
    // ----------------------------------------------------------------

    val dummyAuthor = Author(
        id = 1,
        name = "John Doe",
        follower = 100
    )

    val dummyAuthorList = (1L..100L).map { index ->
        dummyAuthor.copy(
            id = index,
            name = "John Doe $index",
            follower = index * 10
        )
    }

    // ----------------------------------------------------------------
    // Category
    // ----------------------------------------------------------------

    val dummyCategory = Categories(
        id = 1,
        name = "Category"
    )

    val dummyCategoryList = (1L..100L).map { index ->
        dummyCategory.copy(
            id = index,
            name = if (index % 2 == 0L) "Islamic" else "Comics"
        )
    }

    // ----------------------------------------------------------------
    // Books
    // ----------------------------------------------------------------

    val dummyBook = Book(
        id = 1,
        bookName = "Islamic book",
        authorName = dummyAuthor.name,
        price = 200,
        copies = 1000,
        category = dummyAuthorList[0].name,
        releaseDate = Random.nextInt(2013..2023)
    )

    val dummyBookList = (1L..100L).map { index ->
        dummyBook.copy(
            id = index,
            bookName = "Book $index",
            authorName = dummyAuthorList[index.toInt()].name,
            price = (index * 150).toInt(),
            copies = index * 250,
            category = dummyCategoryList[index.toInt()].name,
            releaseDate = Random.nextInt(2013..2023),
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
        )
    }
}
