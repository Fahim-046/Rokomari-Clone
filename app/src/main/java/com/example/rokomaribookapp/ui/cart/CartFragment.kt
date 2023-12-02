package com.example.rokomaribookapp.ui.cart

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rokomaribookapp.R
import com.example.rokomaribookapp.adapters.CartAdapter
import com.example.rokomaribookapp.databinding.FragmentCartBinding
import com.example.rokomaribookapp.models.Cart
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartFragment : Fragment(R.layout.fragment_cart) {
    private lateinit var binding: FragmentCartBinding
    private lateinit var adapter: CartAdapter
    private val viewModel: CartViewModel by viewModels()
    private var isChecked: Boolean = true
    var price: Int = 0
    var id: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()
        adapter = CartAdapter(
            { item, check ->
                adapterOnChecked(item, check)
            },
            { item, amount ->
                adapterOnAmountChange(item, amount)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartBinding.bind(view)
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
            binding.totalAmountTv.text = "Total: ৳ $it"
            price = it.toInt()
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
        binding.cartRv.layoutManager = LinearLayoutManager(requireContext())
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
        binding.topBar.setNavigationOnClickListener {
            requireActivity().finish()
        }
        binding.shippingBtn.setOnClickListener {
            lifecycleScope.launch {
                binding.progressBar.isIndeterminate = true
                delay(2000)
                binding.progressBar.isIndeterminate = false
                val action =
                    CartFragmentDirections.actionCartFragment2ToOrderFragment(
                        price
                    )
                findNavController().navigate(action)
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
}
