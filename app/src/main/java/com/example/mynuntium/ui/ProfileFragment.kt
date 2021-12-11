package com.example.mynuntium.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mynuntium.App
import com.example.mynuntium.R
import com.example.mynuntium.databinding.FragmentProfileBinding
import com.example.mynuntium.utils.MySharedPreference
import com.example.mynuntium.utils.ThemeHelper
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class ProfileFragment : Fragment() {

    private lateinit var binding:FragmentProfileBinding

    @Inject
    lateinit var mySharedPreference: MySharedPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        App.appComponent.injectProfileFragment(this)

        binding.languageLl.setOnClickListener {
            val bottomNavigationView : BottomNavigationView = activity?.findViewById(R.id.bottom_nav)!!
            bottomNavigationView.visibility = View.GONE
            findNavController().navigate(R.id.languageFragment)
        }

        if(mySharedPreference.getPreferences("isDark")=="1"){
            ThemeHelper.applyTheme(ThemeHelper.darkMode)
            binding.switchView.isChecked = true
        }else{
            ThemeHelper.applyTheme(ThemeHelper.lightMode)
            binding.switchView.isChecked = false
        }

        binding.switchView.setOnCheckedChangeListener{compoundButton,b ->
            if(b){
                mySharedPreference.setPreferences("isDark","1")
                ThemeHelper.applyTheme(ThemeHelper.darkMode)
            }else{
                ThemeHelper.applyTheme(ThemeHelper.lightMode)
                mySharedPreference.setPreferences("isDark","0")
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val bottomNavigationView : BottomNavigationView = activity?.findViewById(R.id.bottom_nav)!!
        bottomNavigationView.visibility = View.VISIBLE
    }
}