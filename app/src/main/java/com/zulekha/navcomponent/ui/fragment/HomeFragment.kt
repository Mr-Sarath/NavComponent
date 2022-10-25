package com.zulekha.navcomponent.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.zulekha.navcomponent.R
import com.zulekha.navcomponent.databinding.FragmentHomeBinding
import com.zulekha.navcomponent.ui.adapter.HomeAdapter
import com.zulekha.navcomponent.ui.model.HomeData
import com.zulekha.navcomponent.ui.model.ProductListItem
import com.zulekha.navcomponent.ui.model.RecommendedDataItem
import com.zulekha.navcomponent.ui.utils.shortToast

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private val homeAdapter: HomeAdapter by lazy { HomeAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(
            inflater,
            container,
            false
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleEvents()
        setData()
    }

    private fun handleEvents() {
        binding?.ivAccount?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_accountFragment)
        }

        binding?.rvHome?.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = homeAdapter
        }
        homeAdapter.itemClickListener = { item, innerPosition ->
            when (item) {
                is HomeData.HomeTitle -> {
                }
                is HomeData.HomeRecommended -> {
                    shortToast(item.recommendedList?.get(innerPosition)?.text)
                }
                is HomeData.TopProducts -> {

                }

            }
        }
    }

    private fun setData() {
        val recommendedList = listOf(
            RecommendedDataItem("Trending"),
            RecommendedDataItem("Gean"),
            RecommendedDataItem("shirts"),
            RecommendedDataItem("t Shirts"),
            RecommendedDataItem("Shorts"),
        )
        val productsList = listOf(
            ProductListItem(
                1,
                "Jean",
                "Explore this week’s latest brand item specially for you",
                "AED 400.00"
            ),
            ProductListItem(
                2,
                "Shirts",
                "Explore this week’s latest brand item specially for you",
                "AED 800.00"
            ),
            ProductListItem(
                3,
                "Kurta",
                "Explore this week’s latest brand item specially for you",
                "RS 300.00"
            ),
            ProductListItem(
                4,
                "Shirts full sleeve",
                "Explore this week’s latest brand item specially for you",
                "RS 200.00"
            ),
            ProductListItem(
                5,
                "inner",
                "Explore this week’s latest brand item specially for you",
                "RS 200.00"
            ),
            ProductListItem(
                6,
                "t Shirts",
                "Explore this week’s latest brand item specially for you",
                "RS 400.00"
            )

        )

        val homeProductList = mutableListOf<HomeData>()
        homeProductList.add(HomeData.HomeTitle(0, "Home", "Trending Home Items", emptyList()))
        homeProductList.add(HomeData.HomeRecommended(1, "Recommended", recommendedList))
        homeProductList.add(HomeData.TopProducts(2, "Products", productsList))
        homeAdapter.submitList(homeProductList)
    }


}