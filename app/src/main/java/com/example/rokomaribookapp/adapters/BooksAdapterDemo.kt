package com.example.rokomaribookapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rokomaribookapp.R
import com.example.rokomaribookapp.databinding.BookItemLayoutBinding
import com.example.rokomaribookapp.databinding.BookLayoutBinding
import com.example.rokomaribookapp.models.Book
import com.example.rokomaribookapp.models.Books

class BooksAdapterDemo(
    private val onCLick: (Books) -> Unit
) : ListAdapter<Books, BooksAdapterDemo.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, onCLick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = getItem(position)
        return holder.bind(task)
    }

    class ViewHolder(
        private val binding: BookLayoutBinding,
        private val onClick: (Books) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Books) {
            binding.bookImg.setImageResource(R.drawable.comics_1)

        }

        companion object {
            fun from(
                parent: ViewGroup,
                onCLick: (Books) -> Unit
            ): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = BookLayoutBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, onCLick)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Books>() {
            override fun areItemsTheSame(oldItem: Books, newItem: Books): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Books, newItem: Books): Boolean {
                return oldItem == newItem
            }
        }
    }
}
