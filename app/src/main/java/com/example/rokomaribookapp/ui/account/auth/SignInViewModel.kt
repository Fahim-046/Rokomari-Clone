package com.example.rokomaribookapp.ui.account.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rokomaribookapp.models.User
import com.example.rokomaribookapp.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repo: UserRepository
) : ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState>
        get() = _state

    fun insertUser(user: User) = viewModelScope.launch {
        repo.insert(user)
    }
}
