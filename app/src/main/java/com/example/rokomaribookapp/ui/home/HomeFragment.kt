package com.example.rokomaribookapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup.LayoutParams
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rokomaribookapp.R
import com.example.rokomaribookapp.adapters.BooksAdapterDemo
import com.example.rokomaribookapp.adapters.GenreAdapter
import com.example.rokomaribookapp.databinding.FragmentHomeBinding
import com.example.rokomaribookapp.models.Books
import com.example.rokomaribookapp.models.GenreModel
import com.example.rokomaribookapp.ui.author.AuthorActivity
import com.example.rokomaribookapp.ui.blank.BlankActivity
import com.example.rokomaribookapp.ui.books.BooksActivity
import com.example.rokomaribookapp.ui.products.ProductsActivity
import com.example.rokomaribookapp.utils.extentions.dpToPx
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var genreList: MutableList<GenreModel>
    private lateinit var bookList: MutableList<Books>
    private lateinit var islamicBookList: MutableList<Books>
    private lateinit var comicBookList: MutableList<Books>
    private lateinit var adapter: BooksAdapterDemo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        genreList = mutableListOf(
            GenreModel(0, "Fan and Ventilator"),
            GenreModel(1, "Kids books"),
            GenreModel(2, "Islamic books"),
            GenreModel(3, "Air conditioners"),
            GenreModel(4, "Organic food"),
            GenreModel(5, "Drama"),
            GenreModel(6, "Kitchen appliances"),
            GenreModel(7, "Programming, outsourcing"),
            GenreModel(8, "Stationary product"),
            GenreModel(9, "Self development"),
            GenreModel(10, "Math,Science and technology"),
            GenreModel(11, "Beauty and health product"),
            GenreModel(12, "Admission test"),
            GenreModel(13, "Comics"),
            GenreModel(14, "Science box"),
            GenreModel(15, "Smart watch")
        )
        bookList = mutableListOf(
            Books(1, bookImage = R.drawable.fairy_tale),
            Books(2, bookImage = R.drawable.ekattor_book),
            Books(3, bookImage = R.drawable.fairy_tale),
            Books(4, bookImage = R.drawable.ekattor_book),
            Books(5, bookImage = R.drawable.fairy_tale),
            Books(6, bookImage = R.drawable.ekattor_book)
        )
        islamicBookList = mutableListOf(
            Books(1, bookImage = R.drawable.islamic_1),
            Books(2, bookImage = R.drawable.islamic_2),
            Books(3, bookImage = R.drawable.islamic_1),
            Books(4, bookImage = R.drawable.islamic_2),
            Books(5, bookImage = R.drawable.islamic_1),
            Books(6, bookImage = R.drawable.islamic_2)
        )
        comicBookList = mutableListOf(
            Books(1, bookImage = R.drawable.comics_1),
            Books(2, bookImage = R.drawable.comics2),
            Books(3, bookImage = R.drawable.comics_1),
            Books(4, bookImage = R.drawable.comics2),
            Books(5, bookImage = R.drawable.comics_1),
            Books(6, bookImage = R.drawable.comics2)
        )

        adapter = BooksAdapterDemo {
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        initViews()
        initListeners()
    }

    private fun initViews() {
        binding.adCarousel.registerLifecycle(viewLifecycleOwner)
        val list = mutableListOf<CarouselItem>(
            CarouselItem(R.drawable.ad1),
            CarouselItem(R.drawable.ad2),
            CarouselItem(R.drawable.ad3)
        )
        binding.adCarousel.setData(list)

        binding.genresRv.layoutManager = LinearLayoutManager(requireContext())
        binding.genresRv.setHasFixedSize(true)
        binding.genresRv.adapter = GenreAdapter(genreList)

        binding.selfDevRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.selfDevRv.setHasFixedSize(true)
        adapter.submitList(bookList)
        binding.selfDevRv.adapter = adapter

        binding.islamicBookRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.islamicBookRv.setHasFixedSize(true)
        adapter.submitList(islamicBookList)
        binding.islamicBookRv.adapter = adapter

        binding.comicBookRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.comicBookRv.setHasFixedSize(true)
        adapter.submitList(comicBookList)
        binding.comicBookRv.adapter = adapter
        Log.d("main", "I am in home")
    }

    private fun initListeners() {
        var isExpanded = false
        val notExpanded = 350.dpToPx()
        Log.d("size", notExpanded.toString())
        binding.showTv.setOnClickListener {
            isExpanded = !isExpanded
            val layoutParams = binding.innerCategoryLayout.layoutParams

            if (isExpanded) {
                // Update the height to wrap content
                layoutParams.height = LayoutParams.WRAP_CONTENT
                binding.showTv.text = "Show less category"
            } else {
                layoutParams.height = notExpanded
                binding.showTv.text = "Show all category"
            }
            binding.innerCategoryLayout.layoutParams = layoutParams
        }
        binding.authors.setOnClickListener {
            val intent = Intent(requireActivity(), AuthorActivity::class.java)
            startActivity(intent)
        }
        binding.category.setOnClickListener {
            val intent = Intent(requireActivity(), BooksActivity::class.java)
            startActivity(intent)
        }

        binding.books.setOnClickListener {
            val intent = Intent(requireActivity(), BooksActivity::class.java)
            startActivity(intent)
        }
        binding.electronics.setOnClickListener {
            val x = 1L
            val intent = Intent(requireActivity(), ProductsActivity::class.java)
            intent.putExtra("categoryId", x.toString())
            startActivity(intent)
        }
        binding.kidsZone.setOnClickListener {
            val intent = Intent(requireActivity(), BlankActivity::class.java)
            startActivity(intent)
        }
        binding.monihari.setOnClickListener {
            val intent = Intent(requireActivity(), BlankActivity::class.java)
            startActivity(intent)
        }
        binding.ebooks.setOnClickListener {
            val intent = Intent(requireActivity(), BlankActivity::class.java)
            startActivity(intent)
        }
        binding.inStock.setOnClickListener {
            val intent = Intent(requireActivity(), BlankActivity::class.java)
            startActivity(intent)
        }
    }
}
