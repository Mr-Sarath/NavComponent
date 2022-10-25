package com.zulekha.navcomponent.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zulekha.navcomponent.databinding.ItemHomeRecommendedBinding
import com.zulekha.navcomponent.ui.model.RecommendedDataItem


class HomeRecommendedAdapter(private val listener: ItemClickListener) :
    RecyclerView.Adapter<HomeRecommendedAdapter.MyViewHolder>() {
    private val itemList = mutableListOf<RecommendedDataItem>()
    private var selectedItem = -1

    inner class MyViewHolder(val binding: ItemHomeRecommendedBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemHomeRecommendedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(itemList[position]) {
            holder.binding.tvHomeItem.text = text
            if (selectedItem == position) {
                holder.binding.cvHomeItem.setCardBackgroundColor(Color.parseColor("#00FF00"))
                holder.binding.cvHomeItem.strokeColor = Color.parseColor("#51C989")
                holder.binding.tvHomeItem.setTextColor(Color.WHITE)

            } else {
                holder.binding.cvHomeItem.setCardBackgroundColor(Color.WHITE)
                holder.binding.cvHomeItem.strokeColor = Color.BLACK
                holder.binding.tvHomeItem.setTextColor(Color.BLACK)
            }
            holder.itemView.setOnClickListener {
                selectedItem = holder.adapterPosition
                listener.onRecommendedItemClick(position)
                notifyDataSetChanged()
            }
        }
    }

    fun insertList(list: List<RecommendedDataItem>) {
        itemList.clear()
        itemList.addAll(list)
        notifyItemRangeInserted(0, itemList.size)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    interface ItemClickListener {
        fun onRecommendedItemClick(position: Int)
    }
}