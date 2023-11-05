package com.example.rokomaribookapp.ui.cart

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rokomaribookapp.adapters.CartAdapter
import com.example.rokomaribookapp.databinding.ActivityCartBinding
import com.example.rokomaribookapp.models.Cart
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var adapter: CartAdapter
    private val viewModel: CartViewModel by viewModels()
    private var isChecked: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("main", "Cart activity")

        binding.topBar.setNavigationOnClickListener {
            finish()
        }

        initObserver()
        adapter = CartAdapter(
            { item, check ->
                adapterOnChecked(item, check)
            },
            { item, amount ->
                adapterOnAmountChange(item, amount)
            }
        )
        initViews()
        initListeners()
        loadData()
    }

    private fun initObserver() {
        viewModel.items.observe(this) {
            if (it.isEmpty()) {
                binding.noItemImg.visibility = View.VISIBLE
                binding.noItemTv.visibility = View.VISIBLE
            } else {
                binding.noItemImg.visibility = View.GONE
                binding.noItemTv.visibility = View.GONE
            }
            adapter.submitList(it)
        }
        viewModel.amount.observe(this) {
            binding.totalAmountTv.text = "Total: $it Tk."
        }
        viewModel.items.observe(this) { list ->
            viewModel.selectedItemCount.observe(this) { count ->
                binding.allChecked.isChecked = count >= list.size
                Log.d("count", "$count ${list.size}")
                binding.totalItemTv.text = "Select All ($count/${list.size}) Items"
            }
        }
    }

    private fun initViews() {
        binding.cartRv.layoutManager = LinearLayoutManager(this)
        binding.cartRv.adapter = adapter
    }

    private fun initListeners() {
        binding.allChecked.setOnClickListener {
            if (binding.allChecked.isChecked) {
                viewModel.unSelectAll(true)
            } else {
                viewModel.unSelectAll(false)
            }
        }
    }

    private fun loadData() {
        viewModel.getAll()
    }

    private fun adapterOnChecked(cart: Cart, check: Boolean) {
        viewModel.update(cart, check)
    }

    private fun adapterOnAmountChange(cart: Cart, amount: Int) {
        if (amount > 0) {
            viewModel.update(cart, cart.isSelected, amount)
        } else {
            viewModel.deleteItem(cart)
        }
    }

    override fun onBackPressed() {
        finish()
    }
}
