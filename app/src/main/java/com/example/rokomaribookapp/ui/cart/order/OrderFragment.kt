package com.example.rokomaribookapp.ui.cart.order

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.rokomaribookapp.R
import com.example.rokomaribookapp.databinding.FragmentOrderBinding

class OrderFragment : Fragment(R.layout.fragment_order) {
    private val args: OrderFragmentArgs by navArgs()
    private lateinit var binding: FragmentOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOrderBinding.bind(view)
        initViews()
        initListeners()
    }

    private fun initListeners() {
        binding.topBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.creditCardLayout.setOnClickListener {
            binding.doneCb.visibility = View.VISIBLE
            binding.doneCod.visibility = View.INVISIBLE
        }
        binding.codLayout.setOnClickListener {
            binding.doneCod.visibility = View.VISIBLE
            binding.doneCb.visibility = View.INVISIBLE
        }
    }

    private fun initViews() {
        binding.totalPrice.text = "৳ ${args.totalPrice}"
    }
}
