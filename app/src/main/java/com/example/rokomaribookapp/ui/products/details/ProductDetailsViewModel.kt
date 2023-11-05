package com.example.rokomaribookapp.ui.products.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rokomaribookapp.models.ProductsWithCategory
import com.example.rokomaribookapp.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _products: MutableLiveData<ProductsWithCategory> by lazy {
        MutableLiveData<ProductsWithCategory>()
    }

    val products: LiveData<ProductsWithCategory>
        get() = _products

    fun getProducts(id: Long) = viewModelScope.launch {
        try {
            val response = productRepository.getItem(id)
            _products.value = response
        } catch (e: Exception) {
        }
    }
}
