package com.example.rokomaribookapp.ui.books.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.rokomaribookapp.R
import com.example.rokomaribookapp.databinding.FragmentBookDetailsBinding

class BookDetailsFragment : Fragment(R.layout.fragment_book_details) {
    private lateinit var binding: FragmentBookDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBookDetailsBinding.bind(view)
    }
}
