package com.example.rokomaribookapp.ui.books.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rokomaribookapp.models.Book
import com.example.rokomaribookapp.models.Cart
import com.example.rokomaribookapp.repositories.BookRepository
import com.example.rokomaribookapp.repositories.CartRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    private val cartRepository: CartRepository,
    private val auth: FirebaseAuth
) : ViewModel() {
    private val _books: MutableLiveData<List<Book>> by lazy {
        MutableLiveData<List<Book>>()
    }

    val books: LiveData<List<Book>>
        get() = _books

    private val _showMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val showMessage: LiveData<String>
        get() = _showMessage

    fun getBooks(
        category: String
    ) = viewModelScope.launch {
        try {
            val response = bookRepository.getBYCategory(category)
            _books.value = response
        } catch (e: Exception) {
        }
    }

    fun addToCart(book: Book) = viewModelScope.launch {
        val count = cartRepository.count(book.id)
        if (count >= 1) {
            _showMessage.value = "Already added to cart"
            return@launch
        } else {
            cartRepository.insert(
                Cart(
                    0,
                    book.id,
                    book.bookName,
                    book.price,
                    userId = auth.currentUser?.uid
                )
            )
            _showMessage.value = "Added to cart successfully"
        }
    }
}
