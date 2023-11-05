package com.example.rokomaribookapp.ui.books.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rokomaribookapp.R
import com.example.rokomaribookapp.adapters.CategoryAdapter
import com.example.rokomaribookapp.databinding.FragmentCategoryListBinding
import com.example.rokomaribookapp.models.Categories
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryListFragment : Fragment(R.layout.fragment_category_list) {

    private lateinit var binding: FragmentCategoryListBinding

    private val viewModel: CategoryListViewModel by viewModels()

    private lateinit var adapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initObserver()

        adapter = CategoryAdapter { categories ->
            adapterOnClick(categories)
        }
    }

    private fun adapterOnClick(categories: Categories) {
        val action =
            CategoryListFragmentDirections.actionCategoryListFragmentToBookListFragment(
                categories.name
            )
        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCategoryListBinding.bind(view)

        initViews()
        initListeners()
        loadData()
    }

    private fun loadData() {
        viewModel.getAll()
    }

    private fun initListeners() {
        binding.topBar.setNavigationOnClickListener {
            requireActivity().finish()
        }
    }

    private fun initViews() {
        binding.categoryRv.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.categoryRv.adapter = adapter
    }

    private fun initObserver() {
        viewModel.categories.observe(this) {
            adapter.submitList(it)
        }
    }
}
