package com.example.rokomaribookapp.ui.products.list

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
class ProductListViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _products: MutableLiveData<List<ProductsWithCategory>> by lazy {
        MutableLiveData<List<ProductsWithCategory>>()
    }

    val products: LiveData<List<ProductsWithCategory>>
        get() = _products

    fun getProducts(id: Long) = viewModelScope.launch {
        try {
            val response = productRepository.getList(id)
            _products.value = response
        } catch (e: Exception) {
        }
    }
}
