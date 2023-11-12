package com.example.rokomaribookapp.ui.products.singleProduct

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.rokomaribookapp.R
import com.example.rokomaribookapp.databinding.FragmentSingleProductDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

@AndroidEntryPoint
class SingleProductDetailsFragment : Fragment(R.layout.fragment_single_product_details) {
    private val args: SingleProductDetailsFragmentArgs by navArgs()
    private val viewModel: SingleProductDetailsViewModel by viewModels()

    private lateinit var binding: FragmentSingleProductDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSingleProductDetailsBinding.bind(view)
        // initViews()
        initListeners()
        loadData()
    }

    private fun initObserver() {
        viewModel.product.observe(this) {
            binding.nameTv.text = it.name
            binding.brandNameTv.text = it.brand
            binding.price.text = "${it.price} Tk."
            binding.priceTv.text = "${it.price} Tk."
            binding.titleNameTv.text = it.name
            binding.modelTv.text = it.model
            binding.companyTv.text = it.brand
            setImage(it.categoryId)
        }
        viewModel.category.observe(this) {
            binding.categoryNameTv.text = it
            binding.categoryTv.text = it
        }
    }

    private fun setImage(id: Long) {
        binding.carousel.registerLifecycle(viewLifecycleOwner)
        val list = mutableListOf<CarouselItem>()
        when (id) {
            2L -> {
                binding.topBar.title = "Kitchen Appliances"
                for (index in 1..3) {
                    list.add(
                        CarouselItem(
                            imageDrawable = R.drawable.mixer_grinder
                        )
                    )
                }
            }

            3L -> {
                binding.topBar.title = "Irons"
                for (index in 1..3) {
                    list.add(
                        CarouselItem(
                            imageDrawable = R.drawable.iron
                        )
                    )
                }
            }

            4L -> {
                binding.topBar.title = "Smart Watches"
                for (index in 1..3) {
                    list.add(
                        CarouselItem(
                            imageDrawable = R.drawable.smart_watch
                        )
                    )
                }
            }

            5L -> {
                binding.topBar.title = "Rice Cooker"
                for (index in 1..3) {
                    list.add(
                        CarouselItem(
                            imageDrawable = R.drawable.rice_cooker
                        )
                    )
                }
            }
        }
        binding.carousel.setData(list)
    }

    private fun initViews() {
    }

    private fun initListeners() {
        binding.topBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun loadData() {
        viewModel.loadData(args.id)
    }
}
