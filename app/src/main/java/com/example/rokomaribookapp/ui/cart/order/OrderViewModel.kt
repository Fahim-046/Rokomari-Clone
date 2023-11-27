package com.example.rokomaribookapp.ui.cart.order

import androidx.lifecycle.ViewModel
import com.example.rokomaribookapp.repositories.CartRepository
import com.example.rokomaribookapp.repositories.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderRepo: OrderRepository,
    private val cartRepository: CartRepository
):ViewModel() {
}