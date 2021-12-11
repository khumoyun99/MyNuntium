package com.example.mynuntium.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mynuntium.App
import com.example.mynuntium.R
import com.example.mynuntium.adapters.BookmarksRvAdapter
import com.example.mynuntium.databinding.FragmentBookmarksBinding
import com.example.mynuntium.databse.NewsEntity
import com.example.mynuntium.databse.NewsLikeEntity
import com.example.mynuntium.viewmodels.NewsResource
import com.example.mynuntium.viewmodels.NewsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class BookmarksFragment : Fragment(),CoroutineScope {

    private lateinit var binding:FragmentBookmarksBinding
    private lateinit var bookmarksRvAdapter: BookmarksRvAdapter
    private val TAG = "BookmarksFragment"


    @Inject
    lateinit var newsViewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        App.appComponent.injectBookmarks(this)
        binding = FragmentBookmarksBinding.inflate(inflater,container,false)
        bookmarksRvAdapter = BookmarksRvAdapter(object :BookmarksRvAdapter.OnItemClickListener{
            override fun onClick(newsEntity: NewsEntity) {
                val bundle = Bundle()
                bundle.putSerializable("news",newsEntity)
                findNavController().navigate(R.id.articlePageFragment,bundle)
            }
        })


        launch {
            val bookmarksList = ArrayList(newsViewModel.getAllLikelyNews())
            if(bookmarksList.size!=0){
                binding.ellipse.visibility = View.GONE
                binding.bookImg.visibility = View.GONE
                binding.noBookmarks.visibility = View.GONE

                binding.bookmarksRv.visibility = View.VISIBLE

                bookmarksRvAdapter.submitList(bookmarksList)
                binding.bookmarksRv.adapter = bookmarksRvAdapter
            }else{
                binding.ellipse.visibility = View.VISIBLE
                binding.bookImg.visibility = View.VISIBLE
                binding.noBookmarks.visibility = View.VISIBLE

                binding.bookmarksRv.visibility = View.GONE

            }
        }

        return binding.root
    }


    override val coroutineContext: CoroutineContext
        get() = Job()+Dispatchers.Main
}