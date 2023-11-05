package com.example.rokomaribookapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rokomaribookapp.R
import com.example.rokomaribookapp.databinding.BookItemLayoutBinding
import com.example.rokomaribookapp.models.Book

class BookAdapter(
    private val onCLick: (Book) -> Unit,
    private val onAdd: (Book) -> Unit
) : ListAdapter<Book, BookAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, onCLick, onAdd)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = getItem(position)
        return holder.bind(task)
    }

    class ViewHolder(
        private val binding: BookItemLayoutBinding,
        private val onClick: (Book) -> Unit,
        private val onAdd: (Book) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Book) {
            var isAdded = false
            binding.bookImg.setImageResource(R.drawable.comics_1)
            binding.bookNameTv.text = item.bookName
            binding.bookAuthorTv.text = item.authorName
            binding.bookPriceTv.text = "${item.price} TK."
            binding.root.setOnClickListener {
                onClick(item)
            }
            binding.addToCartTv.setOnClickListener {
                onAdd(item)
                binding.addToCartTv.setBackgroundResource(R.drawable.add_to_cart_btn_pressed)
                binding.addToCartTv.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.is_pressed
                    )
                )
            }
        }

        companion object {
            fun from(
                parent: ViewGroup,
                onCLick: (Book) -> Unit,
                onAdd: (Book) -> Unit
            ): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = BookItemLayoutBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, onCLick, onAdd)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }
        }
    }
}
