package com.example.mynuntium.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mynuntium.adapters.LanguageRvAdapter
import com.example.mynuntium.databinding.FragmentLanguageBinding

class LanguageFragment : Fragment() {

    private lateinit var binding:FragmentLanguageBinding
    private lateinit var languageRvAdapter: LanguageRvAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLanguageBinding.inflate(inflater,container,false)


        languageRvAdapter = LanguageRvAdapter(object :LanguageRvAdapter.OnItemClickListener{
            override fun onItemClick(item: String) {
                Toast.makeText(requireContext(), item, Toast.LENGTH_SHORT).show()
            }
        })
        binding.languageRv.adapter = languageRvAdapter
        binding.backImg.setOnClickListener {
            findNavController().popBackStack()
        }




        return binding.root
    }


}