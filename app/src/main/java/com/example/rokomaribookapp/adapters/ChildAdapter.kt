package com.example.rokomaribookapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rokomaribookapp.R
import com.example.rokomaribookapp.databinding.ProductChildItemLayoutBinding
import com.example.rokomaribookapp.models.Products

class ChildAdapter(
    private val onClick: (Products) -> Unit
) : ListAdapter<Products, ChildAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = getItem(position)
        return holder.bind(task)
    }

    class ViewHolder(
        private val binding: ProductChildItemLayoutBinding,
        private val onClick: (Products) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Products) {
            binding.productNameTv.text = item.name
            when (item.categoryId) {
                2L -> {
                    binding.productImg.setImageResource(R.drawable.mixer_grinder)
                }

                3L -> {
                    binding.productImg.setImageResource(R.drawable.iron)
                }

                4L -> {
                    binding.productImg.setImageResource(R.drawable.smart_watch)
                }

                5L -> {
                    binding.productImg.setImageResource(R.drawable.rice_cooker)
                }
            }
            binding.productPriceTv.text = item.price.toString() + "Tk."
            binding.root.setOnClickListener {
                onClick(item)
            }
        }

        companion object {
            fun from(
                parent: ViewGroup,
                onClick: (Products) -> Unit
            ): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProductChildItemLayoutBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, onClick)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Products>() {
            override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
                return oldItem == newItem
            }
        }
    }
}
