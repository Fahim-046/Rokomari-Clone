package com.example.rokomaribookapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rokomaribookapp.R
import com.example.rokomaribookapp.databinding.AllAuthorsLayoutBinding
import com.example.rokomaribookapp.databinding.CategoryItemLayoutBinding
import com.example.rokomaribookapp.models.Author
import com.example.rokomaribookapp.models.Categories

class CategoryAdapter(
    private val onCLick: (Categories) -> Unit
) : ListAdapter<Categories, CategoryAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, onCLick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = getItem(position)
        return holder.bind(task)
    }

    class ViewHolder(
        private val binding: CategoryItemLayoutBinding,
        private val onClick: (Categories) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Categories) {
            binding.itemTv.text = item.name
            binding.root.setOnClickListener {
                onClick(item)
            }
        }

        companion object {
            fun from(
                parent: ViewGroup,
                onCLick: (Categories) -> Unit
            ): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CategoryItemLayoutBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, onCLick)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Categories>() {
            override fun areItemsTheSame(oldItem: Categories, newItem: Categories): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Categories, newItem: Categories): Boolean {
                return oldItem == newItem
            }
        }
    }
}