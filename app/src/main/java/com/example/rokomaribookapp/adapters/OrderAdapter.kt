package com.example.rokomaribookapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rokomaribookapp.databinding.OrderlayoutBinding
import com.example.rokomaribookapp.models.Order

class OrderAdapter() : ListAdapter<Order, OrderAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        return holder.bind(item)
    }

    class ViewHolder(
        private val binding: OrderlayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Order) {
            binding.orderIdName.text = item.id.toString()
            binding.name.text = item.customerName
        }

        companion object {
            fun from(
                parent: ViewGroup
            ): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = OrderlayoutBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Order>() {
            override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
                return oldItem == newItem
            }
        }
    }
}
