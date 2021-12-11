package com.example.mynuntium.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mynuntium.App
import com.example.mynuntium.R
import com.example.mynuntium.adapters.NewsRvAdapter
import com.example.mynuntium.adapters.RecommendRvAdapter
import com.example.mynuntium.databinding.BrowseItemRvBinding
import com.example.mynuntium.databinding.FragmentHomepageBinding
import com.example.mynuntium.databse.NewsEntity
import com.example.mynuntium.databse.NewsLikeEntity
import com.example.mynuntium.viewmodels.NewsResource
import com.example.mynuntium.viewmodels.NewsViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class HomepageFragment : Fragment(),CoroutineScope {

    private lateinit var binding:FragmentHomepageBinding
    private lateinit var newsRvAdapter: NewsRvAdapter
    private lateinit var recommendRvAdapter: RecommendRvAdapter
    private val TAG = "HomepageFragment"
    val tabList = arrayListOf(
        "All",
        "Sports",
        "Gaming",
        "Politics",
        "Life",
        "Animals",
        "Nature",
        "Food",
        "Art",
        "History",
        "Fashion",
        "Covid-19",
        "Middle East"
    )

    @Inject
    lateinit var newViewModel: NewsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        App.appComponent.injectHomepage(this)
        binding = FragmentHomepageBinding.inflate(inflater,container,false)



        Log.d(TAG, "onCreateView: onCreateView")

        setTabs()


        val activated = binding.tablayout.isActivated
        Toast.makeText(requireContext(), activated.toString(), Toast.LENGTH_SHORT).show()
        if(!activated){
            launch {
                newViewModel.getAllNews(tabList[0])
                    .collect {
                        when(it){
                            is NewsResource.Loading->{
                                binding.progressBarRv.visibility = View.GONE
                                Log.d(TAG, "onCreateView: Loading")
                            }
                            is NewsResource.Error->{
                                binding.progressBarRv.visibility = View.GONE
                                Log.d(TAG, "onCreateView: ${it.message}")
                            }
                            is NewsResource.Success->{
                                binding.progressBarRv.visibility = View.GONE
                                Log.d(TAG, "onCreateView: ${it.list}")
                                newsRvAdapter.submitList(it.list)
                                binding.rv.adapter = newsRvAdapter
                            }
                        }
                    }
            }
        }
        newsRvAdapter = NewsRvAdapter(object :NewsRvAdapter.OnItemClickListener{
            override fun onItemClick(
                newsEntity: NewsEntity,
                browseItemRvBinding: BrowseItemRvBinding
            ) {
                if(newsEntity.isLike){
                    browseItemRvBinding.bookmarksImage.setImageResource(R.drawable.ic_bookmarks_unselected)
                    newsEntity.isLike = false
                }else{
                    browseItemRvBinding.bookmarksImage.setImageResource(R.drawable.ic_bookmarks_selected)
                    newsEntity.isLike = true
                }
                launch {
                    newViewModel.updateItemNews(newsEntity)
                    if(newsEntity.isLike){
                        newViewModel.addLikeNews(NewsLikeEntity(
                            author = newsEntity.author,
                            content = newsEntity.content,
                            description = newsEntity.description,
                            publishedAt = newsEntity.publishedAt,
                            urlToImage = newsEntity.urlToImage,
                            title = newsEntity.title,
                            isLike = newsEntity.isLike,
                            type = newsEntity.type
                        ))
                    }else{
                        newViewModel.deleteLikeNews(NewsLikeEntity(
                            author = newsEntity.author,
                            content = newsEntity.content,
                            description = newsEntity.description,
                            publishedAt = newsEntity.publishedAt,
                            urlToImage = newsEntity.urlToImage,
                            title = newsEntity.title,
                            isLike = newsEntity.isLike,
                            type = newsEntity.type
                        ))
                    }

                }
            }

            override fun onClick(newItem: NewsEntity) {
                val bundle = Bundle()
                bundle.putSerializable("news",newItem)
                val bottomNavigationView : BottomNavigationView = activity?.findViewById(R.id.bottom_nav)!!
                bottomNavigationView.visibility = View.GONE
                findNavController().navigate(R.id.articlePageFragment,bundle)
            }
        })

        binding.tablayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
//                Toast.makeText(requireContext(), "${tab?.position}", Toast.LENGTH_SHORT).show()
                val pos = tab?.position?:0
                launch {
                    newViewModel.getAllNews(tabList[pos])
                        .collect {
                            when(it){
                                is NewsResource.Loading->{
                                    binding.progressBarRv.visibility = View.GONE
                                    Log.d(TAG, "onCreateView: Loading")
                                }
                                is NewsResource.Error->{
                                    binding.progressBarRv.visibility = View.GONE
                                    Log.d(TAG, "onCreateView: ${it.message}")
                                }
                                is NewsResource.Success->{
                                    binding.progressBarRv.visibility = View.GONE
                                    Log.d(TAG, "onCreateView: ${it.list}")
                                    newsRvAdapter.submitList(it.list)
                                    binding.rv.adapter = newsRvAdapter
                                }
                            }
                        }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })



        launch {
            newViewModel.getAllRecommendNews("Life")
                .collect {
                    when(it){
                        is NewsResource.Loading->{
                            Log.d(TAG, "onViewCreated: Loading")
                        }
                        is NewsResource.Error->{
                            Log.d(TAG, "onViewCreated: ${it.message}")
                        }
                        is NewsResource.Success->{
                            val newsEntityRecommendList = ArrayList<NewsEntity>(it.list)
                            recommendRvAdapter = RecommendRvAdapter(true,newsEntityRecommendList,object :RecommendRvAdapter.OnItemClickListener{
                                override fun onItemClick(newsEntity: NewsEntity) {
                                    val bundle = Bundle()
                                    bundle.putSerializable("news",newsEntity)
                                    val bottomNavigationView : BottomNavigationView = activity?.findViewById(R.id.bottom_nav)!!
                                    bottomNavigationView.visibility = View.GONE
                                    findNavController().navigate(R.id.articlePageFragment,bundle)
                                }
                            })
                            binding.recommendRv.adapter = recommendRvAdapter
                        }
                    }
                }
        }

        binding.seeAll.setOnClickListener {
            val bottomNavigationView : BottomNavigationView = activity?.findViewById(R.id.bottom_nav)!!
            bottomNavigationView.visibility = View.GONE
            findNavController().navigate(R.id.recommendAllFragment)
        }



        return binding.root

    }



    override val coroutineContext: CoroutineContext
        get() = Job()+Dispatchers.Main

    private fun setTabs() {
        for (i in 0 until binding.tablayout.tabCount) {
            val tab = (binding.tablayout.getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as MarginLayoutParams
            p.setMargins(0, 0, 35, 0)
            tab.requestLayout()
        }
    }

    override fun onResume() {
        super.onResume()
        val bottomNavigationView : BottomNavigationView = activity?.findViewById(R.id.bottom_nav)!!
        bottomNavigationView.visibility = View.VISIBLE
    }



}