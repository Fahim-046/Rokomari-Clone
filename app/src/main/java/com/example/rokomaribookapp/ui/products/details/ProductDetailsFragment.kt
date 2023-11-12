package com.example.rokomaribookapp.ui.products.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rokomaribookapp.R
import com.example.rokomaribookapp.adapters.ChildAdapter
import com.example.rokomaribookapp.databinding.FragmentProductDetailsBinding
import com.example.rokomaribookapp.models.Products
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment(R.layout.fragment_product_details) {
    private val args: ProductDetailsFragmentArgs by navArgs()
    private val viewModel: ProductDetailsViewModel by viewModels()
    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var adapter: ChildAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()
        adapter = ChildAdapter { product ->
            adapterOnClick(product)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductDetailsBinding.bind(view)
        initViews()
        initListeners()
        loadData()
    }

    private fun loadData() {
        viewModel.getProducts(args.categoryId)
    }

    private fun initListeners() {
        binding.topBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initViews() {
        binding.productsRv.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.productsRv.adapter = adapter
    }

    private fun initObserver() {
        viewModel.products.observe(this) { list ->
            binding.topBar.title = list.category.name
            adapter.submitList(list.products)
        }
    }

    private fun adapterOnClick(product: Products) {
        val action =
            ProductDetailsFragmentDirections.actionProductDetailsFragmentToSingleProductDetailsFragment(
                product.id
            )
        findNavController().navigate(action)
    }
}
