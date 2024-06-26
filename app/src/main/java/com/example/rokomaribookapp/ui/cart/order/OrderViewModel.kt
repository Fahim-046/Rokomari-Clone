package com.example.rokomaribookapp.ui.cart.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rokomaribookapp.models.Cart
import com.example.rokomaribookapp.models.Order
import com.example.rokomaribookapp.repositories.CartRepository
import com.example.rokomaribookapp.repositories.OrderRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderRepo: OrderRepository,
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _items: MutableLiveData<List<Cart>> by lazy {
        MutableLiveData<List<Cart>>()
    }

    val items: LiveData<List<Cart>>
        get() = _items

    fun confirmOrder(order: Order) = viewModelScope.launch {
        orderRepo.insertOrderWithCartItems(order)
    }

    fun getCartInfo() = viewModelScope.launch {
        val response = cartRepository.getAll(Firebase.auth.currentUser?.uid)
        _items.value = response
    }

    fun deleteFromCart(userId: String) = viewModelScope.launch {
        cartRepository.deleteAll(userId)
    }
}
