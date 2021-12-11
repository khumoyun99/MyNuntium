package com.example.mynuntium.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mynuntium.R
import com.example.mynuntium.adapters.ImageViewPagerAdapter
import com.example.mynuntium.databinding.FragmentOnboardingBinding


class OnboardingFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingBinding
    private lateinit var imageViewPagerAdapter: ImageViewPagerAdapter

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentOnboardingBinding.inflate(inflater,container,false)
        imageViewPagerAdapter = ImageViewPagerAdapter()

        binding.viewPagerImage.adapter = imageViewPagerAdapter
        binding.dotsIndicator.setViewPager2(binding.viewPagerImage)


        binding.nextCv.setOnClickListener {
          findNavController().navigate(R.id.wellcomeScreenFragment)
        }






        return binding.root
    }

}