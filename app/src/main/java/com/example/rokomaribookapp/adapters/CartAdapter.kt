package com.example.rokomaribookapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rokomaribookapp.databinding.CartItemBinding
import com.example.rokomaribookapp.models.Cart

class CartAdapter(
    private val onChecked: (Cart, Boolean) -> Unit,
    private val onChanged: (Cart, Int) -> Unit
) : ListAdapter<Cart, CartAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, onChecked, onChanged)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = getItem(position)
        return holder.bind(task)
    }

    class ViewHolder(
        private val binding: CartItemBinding,
        private val onChecked: (Cart, Boolean) -> Unit,
        private val onChanged: (Cart, Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Cart) {
            var count = item.itemAmount
            binding.check.isChecked = item.isSelected
            binding.productName.text = item.itemName
            binding.productPrice.text = "${item.itemPrice} Tk."
            binding.productAmount.text = item.itemAmount.toString()
            binding.check.setOnClickListener {
                Log.d("adapter", "${binding.check.isChecked}")
                onChecked(item, binding.check.isChecked)
            }
            binding.incrementBtn.setOnClickListener {
                count++
                onChanged(item, count)
            }
            binding.decrementBtn.setOnClickListener {
                count--
                onChanged(item, count)
            }
        }

        companion object {
            fun from(
                parent: ViewGroup,
                onChecked: (Cart, Boolean) -> Unit,
                onChanged: (Cart, Int) -> Unit
            ): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CartItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, onChecked, onChanged)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Cart>() {
            override fun areItemsTheSame(oldItem: Cart, newItem: Cart): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Cart, newItem: Cart): Boolean {
                return oldItem == newItem
            }
        }
    }
}
