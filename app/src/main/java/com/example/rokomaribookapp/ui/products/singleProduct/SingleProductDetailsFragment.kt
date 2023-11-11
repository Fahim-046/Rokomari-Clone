package com.example.rokomaribookapp.ui.products.singleProduct

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.rokomaribookapp.R
import com.example.rokomaribookapp.databinding.FragmentSingleProductDetailsBinding
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class SingleProductDetailsFragment : Fragment(R.layout.fragment_single_product_details) {
    private val args: SingleProductDetailsFragmentArgs by navArgs()

    private lateinit var binding: FragmentSingleProductDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // initObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSingleProductDetailsBinding.bind(view)
        initViews()
        // initListeners()
        // loadData()
    }

    private fun initObserver() {
        TODO("Not yet implemented")
    }

    private fun initViews() {
        binding.carousel.registerLifecycle(viewLifecycleOwner)
        val list = mutableListOf<CarouselItem>()
        when (args.id) {
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

    private fun initListeners() {
        TODO("Not yet implemented")
    }

    private fun loadData() {
        TODO("Not yet implemented")
    }
}
