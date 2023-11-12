package com.example.rokomaribookapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rokomaribookapp.databinding.ProductParentItemLayoutBinding
import com.example.rokomaribookapp.models.Products
import com.example.rokomaribookapp.models.ProductsWithCategory

class ParentAdapter(
    private val onCLick: (Long) -> Unit,
    private val onDetailsClick: (Products) -> Unit
) : ListAdapter<ProductsWithCategory, ParentAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, onCLick, onDetailsClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = getItem(position)
        return holder.bind(task)
    }

    class ViewHolder(
        private val binding: ProductParentItemLayoutBinding,
        private val onClick: (Long) -> Unit,
        private val onDetailsClick: (Products) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        private val viewPool = RecyclerView.RecycledViewPool()

        fun bind(item: ProductsWithCategory) {
            val adapter = ChildAdapter { products ->
                onDetailsClick(products)
            }
            binding.titleTv.text = item.category.name
            binding.childListRv.layoutManager = GridLayoutManager(binding.root.context, 2)
            binding.childListRv.adapter = adapter
            adapter.submitList(item.products.subList(0, 4))
            binding.viewAllBtn.setOnClickListener {
                onClick(item.category.id)
            }
        }

        companion object {
            fun from(
                parent: ViewGroup,
                onCLick: (Long) -> Unit,
                onDetailsClick: (Products) -> Unit
            ): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProductParentItemLayoutBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, onCLick, onDetailsClick)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductsWithCategory>() {
            override fun areItemsTheSame(
                oldItem: ProductsWithCategory,
                newItem: ProductsWithCategory
            ): Boolean {
                return oldItem.category.id == newItem.category.id
            }

            override fun areContentsTheSame(
                oldItem: ProductsWithCategory,
                newItem: ProductsWithCategory
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
