package com.zulekha.navcomponent.ui.model

sealed class HomeData {

    class HomeTitle(
        val id: Int,
        val title: String,
        val subTitle: String,
        val Heading: List<String>? = null

    ) : HomeData()

    class HomeRecommended(
        val id: Int,
        val title: String,
        val recommendedList: List<RecommendedDataItem>? = null

    ) : HomeData()

    class TopProducts(
        val id: Int,
        val title: String,
        var productList: List<ProductListItem>? = null
    ) : HomeData()
}

