package com.example.mynuntium.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.mynuntium.App
import com.example.mynuntium.R
import com.example.mynuntium.adapters.RecommendRvAdapter
import com.example.mynuntium.databinding.FragmentRecommendAllBinding
import com.example.mynuntium.databse.NewsEntity
import com.example.mynuntium.repository.NewsRepository
import com.example.mynuntium.viewmodels.NewsResource
import com.example.mynuntium.viewmodels.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class RecommendAllFragment : Fragment(),CoroutineScope {

    private lateinit var binding:FragmentRecommendAllBinding
    private lateinit var recommendRvAdapter: RecommendRvAdapter

    @Inject
    lateinit var newsViewModel: NewsViewModel

    @SuppressLint("ShowToast")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        App.appComponent.injectRecommendAllFragment(this)
        binding = FragmentRecommendAllBinding.inflate(inflater,container,false)

        launch {
            newsViewModel.getAllRecommendNews("History")
                .collect {
                    when(it){
                        is NewsResource.Loading->{
                            val snackbar = Snackbar.make(requireView(),"Loading",Snackbar.LENGTH_SHORT).setAction("Action",null)
                            snackbar.setActionTextColor(Color.BLUE)
                            snackbar.show()
                        }
                        is NewsResource.Error->{
                            val snackbar = Snackbar.make(requireView(),"Error",Snackbar.LENGTH_SHORT).setAction("Action",null)
                            snackbar.setActionTextColor(Color.BLUE)
                            snackbar.show()

                        }
                        is NewsResource.Success->{
                            val newsEntityList = ArrayList<NewsEntity>(it.list)
                            recommendRvAdapter = RecommendRvAdapter(false,newsEntityList,object :RecommendRvAdapter.OnItemClickListener{
                                override fun onItemClick(newsEntity: NewsEntity) {
                                    val bundle = Bundle()
                                    bundle.putSerializable("news",newsEntity)
                                    findNavController().navigate(R.id.articlePageFragment,bundle)
                                }
                            })

                            binding.recommend1Rv.adapter = recommendRvAdapter
                        }
                    }
                }
        }


        return binding.root
    }

    override val coroutineContext: CoroutineContext
        get() = Job()+Dispatchers.Main
}