package com.example.rokomaribookapp.ui.products.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rokomaribookapp.models.Cart
import com.example.rokomaribookapp.models.Products
import com.example.rokomaribookapp.models.ProductsWithCategory
import com.example.rokomaribookapp.repositories.CartRepository
import com.example.rokomaribookapp.repositories.ProductRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository
) : ViewModel() {
    private val _products: MutableLiveData<List<ProductsWithCategory>> by lazy {
        MutableLiveData<List<ProductsWithCategory>>()
    }

    val products: LiveData<List<ProductsWithCategory>>
        get() = _products

    private val _added: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val added: LiveData<Boolean>
        get() = _added

    fun getProducts(id: Long) = viewModelScope.launch {
        try {
            val response = productRepository.getList(id)
            _products.value = response
        } catch (e: Exception) {
        }
    }

    fun addToCart(products: Products) = viewModelScope.launch {
        try {
            val response = cartRepository.insert(
                Cart(
                    0,
                    products.id,
                    products.name,
                    products.price,
                    isSelected = true,
                    userId = Firebase.auth.currentUser?.uid
                )
            )
            _added.value = true
        } catch (e: Exception) {
        }
    }
}
