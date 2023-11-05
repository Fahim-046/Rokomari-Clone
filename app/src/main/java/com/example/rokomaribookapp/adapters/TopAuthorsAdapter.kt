package com.example.rokomaribookapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rokomaribookapp.R
import com.example.rokomaribookapp.databinding.TopAuthorsLayoutBinding
import com.example.rokomaribookapp.models.Author

class TopAuthorsAdapter(
    private val onCLick: (Author) -> Unit
) : ListAdapter<Author, TopAuthorsAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, onCLick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = getItem(position)
        return holder.bind(task)
    }

    class ViewHolder(
        private val binding: TopAuthorsLayoutBinding,
        private val onClick: (Author) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Author) {
            binding.authorImg.setImageResource(R.drawable.man)
            binding.authorNameTv.text = item.name
            binding.followerTv.text = "${item.follower} followers"

            binding.root.setOnClickListener {
                onClick(item)
            }
        }

        companion object {
            fun from(
                parent: ViewGroup,
                onCLick: (Author) -> Unit
            ): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TopAuthorsLayoutBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, onCLick)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Author>() {
            override fun areItemsTheSame(oldItem: Author, newItem: Author): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Author, newItem: Author): Boolean {
                return oldItem == newItem
            }
        }
    }
}
