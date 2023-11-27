package com.example.rokomaribookapp.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rokomaribookapp.models.Cart
import com.example.rokomaribookapp.repositories.CartRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val auth: FirebaseAuth
) : ViewModel() {
    private val _items: MutableLiveData<List<Cart>> by lazy {
        MutableLiveData<List<Cart>>()
    }

    val items: LiveData<List<Cart>>
        get() = _items

    private val _amount: MutableLiveData<Long> by lazy {
        MutableLiveData<Long>()
    }

    val amount: LiveData<Long>
        get() = _amount

    private val _selectedItemCount: MutableLiveData<Long> by lazy {
        MutableLiveData<Long>()
    }

    val selectedItemCount: LiveData<Long>
        get() = _selectedItemCount

    fun getAll() = viewModelScope.launch {
        try {
            val response = cartRepository.getAll(auth.currentUser?.uid)
            var sum = 0L
            var count = 0L
            for (index in response) {
                if (index.isSelected) {
                    sum += (index.itemAmount * index.itemPrice)
                    count++
                }
            }
            _selectedItemCount.value = count
            _amount.value = sum
            _items.value = response
        } catch (e: Exception) {
        }
    }

    fun unSelectAll(value: Boolean) = viewModelScope.launch {
        try {
            cartRepository.changeAll(value)
            getAll()
        } catch (e: Exception) {
        }
    }

    fun update(cart: Cart, check: Boolean, amount: Int = 1) = viewModelScope.launch {
        cartRepository.update(cart.copy(isSelected = check, itemAmount = amount))
        getAll()
    }

    fun deleteItem(cart: Cart) = viewModelScope.launch {
        cartRepository.deleteItem(cart)
        getAll()
    }
}
