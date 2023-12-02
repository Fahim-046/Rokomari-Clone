package com.example.rokomaribookapp.ui.cart.order

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.rokomaribookapp.R
import com.example.rokomaribookapp.databinding.FragmentOrderBinding
import com.example.rokomaribookapp.models.Cart
import com.example.rokomaribookapp.models.Order
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OrderFragment : Fragment(R.layout.fragment_order) {
    private val args: OrderFragmentArgs by navArgs()
    private lateinit var binding: FragmentOrderBinding
    private val viewModel: OrderViewModel by viewModels()
    private lateinit var list: List<Cart>
    private var name: String? = null
    private var email: String? = null
    private var method: String? = null
    private val userId = Firebase.auth.currentUser?.uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()
    }

    private fun initObserver() {
        viewModel.items.observe(this) {
            list = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOrderBinding.bind(view)
        initViews()
        initListeners()
        loadData()
    }

    private fun loadData() {
        viewModel.getCartInfo()
    }

    private fun initListeners() {
        binding.topBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.creditCardLayout.setOnClickListener {
            binding.doneCb.visibility = View.VISIBLE
            binding.doneCod.visibility = View.INVISIBLE
            method = "credit/debit card"
        }
        binding.codLayout.setOnClickListener {
            binding.doneCod.visibility = View.VISIBLE
            binding.doneCb.visibility = View.INVISIBLE
            method = "cod"
        }
        binding.confirmBtn.setOnClickListener {
            name = binding.nameEt.text.toString()
            email = binding.emailEt.text.toString()
            if (name?.isNotBlank() == true && email?.isNotBlank() == true && method?.isNotBlank() == true && list.isNotEmpty() && !userId.isNullOrBlank()) { // ktlint-disable max-line-length

                viewModel.confirmOrder(
                    Order(
                        id = 0,
                        customerName = name!!,
                        customerEmail = email!!,
                        paymentMethod = method!!,
                        totalPrice = args.totalPrice,
                        cartItems = list,
                        userId = Firebase.auth.currentUser?.uid!!
                    )
                )
                lifecycleScope.launch {
                    binding.progressBar.isIndeterminate = true
                    viewModel.deleteFromCart(userId)
                    delay(2000)
                    binding.progressBar.isIndeterminate = false
                    Toast.makeText(
                        requireContext(),
                        "Order placed successfully",
                        Toast.LENGTH_SHORT
                    )
                        .show()

                    requireActivity().finish()
                }
            }
        }
    }

    private fun initViews() {
        binding.totalPrice.text = "৳ ${args.totalPrice}"
    }
}
