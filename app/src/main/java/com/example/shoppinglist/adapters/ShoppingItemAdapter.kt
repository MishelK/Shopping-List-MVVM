package com.example.shoppinglist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.data.items.ShoppingItem
import com.example.shoppinglist.databinding.ShoppingItemBinding
import com.example.shoppinglist.viewmodels.ShoppingViewModel

class ShoppingItemAdapter(var items: List<ShoppingItem>, private val viewModel: ShoppingViewModel)
    : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {

        val itemBinding = ShoppingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        holder.updateRowData(items[position])
    }

    inner class ShoppingViewHolder(private val itemBinding: ShoppingItemBinding): RecyclerView.ViewHolder(itemBinding.root) {

        fun updateRowData(shoppingItem: ShoppingItem) {
            itemBinding.tvName.text = shoppingItem.name
            itemBinding.tvAmount.text = shoppingItem.amount.toString()

            itemBinding.ivDelete.setOnClickListener {
                viewModel.delete(shoppingItem)
            }

            itemBinding.ivPlus.setOnClickListener {
                shoppingItem.amount++
                viewModel.upsert(shoppingItem)
            }

            itemBinding.ivMinus.setOnClickListener {
                if (shoppingItem.amount > 0) {
                    shoppingItem.amount--
                    viewModel.upsert(shoppingItem)
                }
            }
        }
    }
}