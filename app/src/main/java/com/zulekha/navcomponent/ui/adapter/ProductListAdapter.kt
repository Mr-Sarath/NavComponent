package com.zulekha.navcomponent.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.zulekha.navcomponent.databinding.ItemProductListBinding
import com.zulekha.navcomponent.ui.model.ProductListItem

class ProductListAdapter(private val listener: ItemProductListClickListener) :
    RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {
    private val itemsList = mutableListOf<ProductListItem>()

    inner class ProductListViewHolder(val binding: ItemProductListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        return ProductListViewHolder(
            ItemProductListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        with(itemsList[position]) {
            holder.binding.tvTitle.text = title
            holder.binding.tvPrice.text = price
            holder.itemView.setOnClickListener {
                listener.onProductItem(this, position)
            }
        }
    }

    fun insertList(list: List<ProductListItem>) {
        itemsList.clear()
        itemsList.addAll(list)
        notifyItemRangeInserted(0, itemsList.size)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}

fun interface ItemProductListClickListener {
    fun onProductItem(productItem: ProductListItem, position: Int)
}



