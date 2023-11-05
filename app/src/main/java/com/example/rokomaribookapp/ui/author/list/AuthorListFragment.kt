package com.example.rokomaribookapp.ui.author.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rokomaribookapp.R
import com.example.rokomaribookapp.adapters.AllAuthorAdapter
import com.example.rokomaribookapp.adapters.TopAuthorsAdapter
import com.example.rokomaribookapp.databinding.FragmentAuthorListBinding
import com.example.rokomaribookapp.models.Author
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorListFragment : Fragment(R.layout.fragment_author_list) {
    private lateinit var binding: FragmentAuthorListBinding
    private val viewModel: AuthorListViewModel by viewModels()

    private lateinit var allAuthorAdapter: AllAuthorAdapter

    private lateinit var topAuthorAdapter: TopAuthorsAdapter

    private var list: List<Author> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()

        allAuthorAdapter = AllAuthorAdapter {
            // onAllAuthorItemClicked(it)
        }
        topAuthorAdapter = TopAuthorsAdapter {
        }
    }

    private fun onAllAuthorItemClicked(author: Author) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthorListBinding.bind(view)

        initViews()
        initListeners()
        loadData()
    }

    private fun initListeners() {
        binding.topBar.setNavigationOnClickListener {
            requireActivity().finish()
        }
    }

    private fun loadData() {
        viewModel.getAuthors()
    }

    private fun initViews() {
        binding.allAuthorsRv.layoutManager = LinearLayoutManager(requireContext())
        binding.allAuthorsRv.adapter = allAuthorAdapter

        binding.topAuthorsRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.topAuthorsRv.adapter = topAuthorAdapter
    }

    private fun initObserver() {
        viewModel.authors.observe(this) { authors ->
            allAuthorAdapter.submitList(authors)
            topAuthorAdapter.submitList(authors)
        }
    }
}
