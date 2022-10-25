package com.zulekha.navcomponent.ui.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewbinding.ViewBinding
import com.zulekha.navcomponent.databinding.RcvHomeViewOneBinding
import com.zulekha.navcomponent.databinding.RcvHomeViewThreeBinding
import com.zulekha.navcomponent.databinding.RcvHomeViewTwoBinding
import com.zulekha.navcomponent.ui.model.HomeData

open class HomeViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    var itemClickListener: ((item: HomeData, innerPosition: Int) -> Unit)? = null

    class HomeHeadingViewHolder(private val binding: RcvHomeViewOneBinding) :
        HomeViewHolder(binding) {
        fun bind(item: HomeData.HomeTitle) {
            binding.tvHead.text = item.title
        }
    }

    class HomeRecommendedViewHolder(private val binding: RcvHomeViewTwoBinding) :
        HomeViewHolder(binding) {
        fun bind(item: HomeData.HomeRecommended) {
            val homeRecommendedAdapter =
                HomeRecommendedAdapter(object : HomeRecommendedAdapter.ItemClickListener {
                    override fun onRecommendedItemClick(position: Int) {
                        itemClickListener?.invoke(item, position)
                    }


                })
            binding.apply {
                tvTitle.text = item.title
                rvItems.apply {
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = homeRecommendedAdapter
                }
                item.recommendedList?.let { homeRecommendedAdapter.insertList(it) }
            }
        }
    }

    class HomeProductViewHolder(private val binding: RcvHomeViewThreeBinding) :
        HomeViewHolder(binding) {
        fun bind(item: HomeData.TopProducts) {

            val productListAdapter =
                ProductListAdapter { _, position ->
                    itemClickListener?.invoke(
                        item,
                        position
                    )
                }
            binding.apply {
                tvProduct.text = item.title

                rvProduct.apply {
                    layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    adapter = productListAdapter

                }
                item.productList?.let { productListAdapter.insertList(it) }
            }
        }
    }
}