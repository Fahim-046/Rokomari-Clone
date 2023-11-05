package com.example.rokomaribookapp.ui.author.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rokomaribookapp.models.Author
import com.example.rokomaribookapp.repositories.AuthorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AuthorListViewModel @Inject constructor(
    private val authorRepository: AuthorRepository
) : ViewModel() {
    private val _authors: MutableLiveData<List<Author>> by lazy {
        MutableLiveData<List<Author>>()
    }

    val authors: LiveData<List<Author>>
        get() = _authors

    fun getAuthors() = viewModelScope.launch {
        try {
            val response = authorRepository.getAll()
            _authors.value = response
        } catch (e: Exception) {
        }
    }
}
