# Navigation Component......
 Navigation component Recycler view Multi Type
 
**Navigation Component..**

Navigation refers to the interactions that allow users to navigate across, into, and back out from the different pieces of content within your app

**Navigation with Bottom navigation**

Navigation with bottom navigation .

**Navigation with Recycler Multi View Type** 

Easier and more flexible to create multiple types for Android RecyclerView.

Previously, when we need to develop a complex RecyclerView / ListView, it is difficult and troublesome work. We should override the `getItemViewType()` of `RecyclerView.Adapter` , add some types, and create some `ViewHolder`s relating to those types

**CODE**

**Main Adapter**

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





**Main Adapter View Holder**


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

# Dependency
    Used Dependency

buildFeatures {  
  viewBinding true  
}

~~~
//size library  
implementation 'com.intuit.sdp:sdp-android:1.0.6'  
implementation 'com.intuit.ssp:ssp-android:1.0.6'
~~~
 ~~~
 //NAVIGATION  
implementation 'androidx.navigation:navigation-fragment-ktx:2.5.2'  
implementation 'androidx.navigation:navigation-ui-ktx:2.5.2'
~~~
