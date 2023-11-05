package com.example.rokomaribookapp.ui.books.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rokomaribookapp.models.Categories
import com.example.rokomaribookapp.repositories.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    private val _categories: MutableLiveData<List<Categories>> by lazy {
        MutableLiveData<List<Categories>>()
    }

    val categories: LiveData<List<Categories>>
        get() = _categories

    fun getAll() = viewModelScope.launch {
        try {
            val response = categoryRepository.getAll()

            _categories.value = response
        } catch (e: Exception) {
        }
    }
}
