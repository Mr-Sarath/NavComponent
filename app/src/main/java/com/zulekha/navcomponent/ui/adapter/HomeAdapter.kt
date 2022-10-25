package com.zulekha.navcomponent.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.zulekha.navcomponent.databinding.RcvHomeViewOneBinding
import com.zulekha.navcomponent.databinding.RcvHomeViewThreeBinding
import com.zulekha.navcomponent.databinding.RcvHomeViewTwoBinding
import com.zulekha.navcomponent.ui.model.HomeData

class HomeAdapter() : ListAdapter<HomeData, HomeViewHolder>(DiffCallBack()) {

    var itemClickListener: ((item: HomeData, innerPosition: Int) -> Unit)? = null

    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
        const val VIEW_TYPE_THREE = 3

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return when (viewType) {
            VIEW_TYPE_ONE -> HomeViewHolder.HomeHeadingViewHolder(
                RcvHomeViewOneBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            VIEW_TYPE_TWO -> HomeViewHolder.HomeRecommendedViewHolder(
                RcvHomeViewTwoBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            else -> {
                HomeViewHolder.HomeProductViewHolder(
                    RcvHomeViewThreeBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            }
        }
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemClickListener = itemClickListener
        when (holder) {
            is HomeViewHolder.HomeHeadingViewHolder -> holder.bind(item as HomeData.HomeTitle)
            is HomeViewHolder.HomeRecommendedViewHolder -> holder.bind(item as HomeData.HomeRecommended)
            is HomeViewHolder.HomeProductViewHolder -> holder.bind(item as HomeData.TopProducts)

        }

    }

    override fun getItemViewType(position: Int): Int {

        return when (getItem(position)) {
            is HomeData.HomeTitle -> VIEW_TYPE_ONE
            is HomeData.HomeRecommended -> VIEW_TYPE_TWO
            else -> {
                VIEW_TYPE_THREE
            }
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<HomeData>() {
        override fun areItemsTheSame(oldItem: HomeData, newItem: HomeData): Boolean {
            return when {
                oldItem is HomeData.HomeTitle && newItem is HomeData.HomeTitle -> {
                    oldItem.id == newItem.id
                }
                oldItem is HomeData.HomeRecommended && newItem is HomeData.HomeRecommended -> {
                    oldItem.id == newItem.id
                }
                oldItem is HomeData.TopProducts && newItem is HomeData.TopProducts -> {
                    oldItem.id == newItem.id
                }
                else -> {
                    false
                }
            }

        }

        override fun areContentsTheSame(oldItem: HomeData, newItem: HomeData): Boolean {
            return when {
                oldItem is HomeData.HomeTitle && newItem is HomeData.HomeTitle -> {
                    oldItem == newItem
                }
                oldItem is HomeData.HomeRecommended && newItem is HomeData.HomeRecommended -> {
                    oldItem == newItem
                }
                oldItem is HomeData.TopProducts && newItem is HomeData.TopProducts -> {
                    oldItem == newItem
                }
                else -> {
                    false
                }
            }
        }
    }

}