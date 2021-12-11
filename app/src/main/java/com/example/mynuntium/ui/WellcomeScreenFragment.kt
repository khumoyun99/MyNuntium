package com.example.mynuntium.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mynuntium.R
import com.example.mynuntium.databinding.FragmentWellcomeScreenBinding


class WellcomeScreenFragment : Fragment() {

    private lateinit var binding:FragmentWellcomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentWellcomeScreenBinding.inflate(inflater,container,false)
        binding.getStartedCv.setOnClickListener {
            findNavController().navigate(R.id.selectTopicFragment)
        }



        return binding.root
    }


}