package com.example.mynuntium.ui

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mynuntium.R
import com.example.mynuntium.adapters.CategoryRvAdapter
import com.example.mynuntium.databinding.CategoryItemRvBinding
import com.example.mynuntium.databinding.FragmentCategoriesBinding

class CategoriesFragment : Fragment() {

    private lateinit var binding:FragmentCategoriesBinding
    private lateinit var categoryRvAdapter: CategoryRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesBinding.inflate(inflater,container,false)
        categoryRvAdapter = CategoryRvAdapter(object :CategoryRvAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, categoryItemRvBinding: CategoryItemRvBinding) {
                categoryItemRvBinding.itemTopic.setBackgroundResource(R.color.topic_color)
                categoryItemRvBinding.topicName.setTextColor(Color.WHITE)

            }
        })
        binding.categoryRv.adapter = categoryRvAdapter

        return binding.root
    }


}