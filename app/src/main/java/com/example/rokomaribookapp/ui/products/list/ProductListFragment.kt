package com.example.rokomaribookapp.ui.products.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rokomaribookapp.R
import com.example.rokomaribookapp.adapters.ParentAdapter
import com.example.rokomaribookapp.databinding.FragmentProductListBinding
import com.example.rokomaribookapp.models.Products
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment() : Fragment(R.layout.fragment_product_list) {
    private val args: ProductListFragmentArgs by navArgs()
    private val viewModel: ProductListViewModel by viewModels()
    private lateinit var adapter: ParentAdapter
    private lateinit var binding: FragmentProductListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()
        adapter = ParentAdapter(
            { id ->
                adapterOnClick(id)
            },
            { product ->
                adapterOnProductClick(product)
            },
            { product ->
                viewModel.addToCart(product)
            }
        )
    }

    private fun initObserver() {
        viewModel.products.observe(this) { list ->
            adapter.submitList(list)
        }
        viewModel.added.observe(this) {
            if (it) {
                Toast.makeText(requireContext(), "Added to cart successfully", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductListBinding.bind(view)
        initViews()
        initListeners()
        loadData()
    }

    private fun loadData() {
        viewModel.getProducts(args.productId)
    }

    private fun initListeners() {
        binding.topBar.setNavigationOnClickListener {
            requireActivity().finish()
        }
    }

    private fun initViews() {
        binding.productsRv.layoutManager = LinearLayoutManager(requireContext())
        binding.productsRv.adapter = adapter
        when (args.productId) {
            1L -> {
                binding.topBar.title = "Electronics"
            }
        }
    }

    private fun adapterOnClick(id: Long) {
        val action =
            ProductListFragmentDirections.actionProductListFragment2ToProductDetailsFragment(
                id
            )
        findNavController().navigate(action)
    }

    private fun adapterOnProductClick(product: Products) {
        val action =
            ProductListFragmentDirections.actionProductListFragment2ToSingleProductDetailsFragment(
                product.id
            )
        findNavController().navigate(action)
    }
}
