package com.example.rokomaribookapp.ui.books.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rokomaribookapp.R
import com.example.rokomaribookapp.adapters.BookAdapter
import com.example.rokomaribookapp.databinding.FragmentBookListBinding
import com.example.rokomaribookapp.models.Book
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookListFragment : Fragment(R.layout.fragment_book_list) {

    private lateinit var binding: FragmentBookListBinding

    private lateinit var adapter: BookAdapter

    private val viewModel: BookListViewModel by viewModels()

    private val args: BookListFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initObserver()

        adapter = BookAdapter(
            { book ->
                adapterOnClick(book)
            },
            { item ->
                viewModel.addToCart(item)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBookListBinding.bind(view)

        initViews()

        initListeners()

        loadData()
    }

    private fun loadData() {
        val category = args.categoryName
        viewModel.getBooks(category)
    }

    private fun initListeners() {
        binding.topBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initViews() {
        binding.booksRv.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.booksRv.adapter = adapter
    }

    private fun initObserver() {
        viewModel.books.observe(this) {
            adapter.submitList(it)
        }
        viewModel.showMessage.observe(this) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun adapterOnClick(book: Book) {
        val action = BookListFragmentDirections.actionBookListFragmentToBookDetailsFragment(book.id)
        findNavController().navigate(action)
    }
}
