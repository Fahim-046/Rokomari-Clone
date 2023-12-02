package com.example.rokomaribookapp.ui.cart.order

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rokomaribookapp.R
import com.example.rokomaribookapp.adapters.OrderAdapter
import com.example.rokomaribookapp.databinding.FragmentOrderPageBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderPageFragment: Fragment(R.layout.fragment_order_page) {
    private lateinit var binding: FragmentOrderPageBinding
    private lateinit var adapter: OrderAdapter
    private val viewModel: OrderPageViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = OrderAdapter()
        initObserver()
    }

    private fun initObserver() {
        viewModel.items.observe(this) {
            adapter.submitList(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOrderPageBinding.bind(view)
        initViews()
        initListeners()
        loadData()
    }

    private fun loadData() {
        Firebase.auth.currentUser?.uid?.let { viewModel.getOrders(it) }
    }

    private fun initListeners() {
        binding.topBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initViews() {
        binding.orderRv.layoutManager = LinearLayoutManager(requireContext())
        binding.orderRv.adapter = adapter
    }
}
