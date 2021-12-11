package com.example.mynuntium.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mynuntium.R
import com.example.mynuntium.adapters.TopicRvAdapter
import com.example.mynuntium.databinding.FragmentSelectTopicBinding
import com.example.mynuntium.databinding.ItemTopicBinding

class SelectTopicFragment : Fragment() {

    private lateinit var binding:FragmentSelectTopicBinding
    private lateinit var topicRvAdapter: TopicRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectTopicBinding.inflate(inflater,container,false)

        topicRvAdapter = TopicRvAdapter(object :TopicRvAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, itemTopicBinding: ItemTopicBinding) {
                itemTopicBinding.itemTopic.setBackgroundResource(R.color.topic_color)
                itemTopicBinding.topicName.setTextColor(Color.WHITE)
            }
        })
        binding.topicRv.adapter = topicRvAdapter

        binding.nextCv.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }

        return binding.root
    }

}