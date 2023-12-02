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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderPageViewModel @Inject constructor(
    private val orderRepo: OrderRepository,
) : ViewModel() {

    private val _items: MutableLiveData<List<Order>> by lazy {
        MutableLiveData<List<Order>>()
    }

    val items: LiveData<List<Order>>
        get() = _items

    fun getOrders(userId:String) = viewModelScope.launch {
        _items.value = orderRepo.getOrders(userId)
    }
}