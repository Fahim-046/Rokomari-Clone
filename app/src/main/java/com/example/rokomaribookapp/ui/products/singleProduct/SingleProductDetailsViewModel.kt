package com.example.rokomaribookapp.ui.products.singleProduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rokomaribookapp.models.Products
import com.example.rokomaribookapp.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class SingleProductDetailsViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {
    private val _product: MutableLiveData<Products> by lazy {
        MutableLiveData<Products>()
    }

    val product: LiveData<Products>
        get() = _product

    private val _category: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val category: LiveData<String>
        get() = _category

    fun loadData(id: Long) = viewModelScope.launch {
        val response = repository.getProduct(id)
        val category = repository.getItem(response.categoryId)
        _product.value = response
        _category.value = category.category.name
    }
}
