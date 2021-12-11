package com.example.mynuntium.ui


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mynuntium.R
import com.example.mynuntium.databinding.FragmentArticlePageBinding
import com.example.mynuntium.databse.NewsEntity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso


class ArticlePageFragment : Fragment() {

    private lateinit var binding:FragmentArticlePageBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticlePageBinding.inflate(inflater,container,false)


//        activity?.window?.setFlags(
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        )




        val bundle = Bundle(arguments)

        val newsEntity:NewsEntity = bundle.getSerializable("news") as NewsEntity

        binding.apply {
            Picasso.get().load(newsEntity.urlToImage).into(articleImg)
            usElectionCv.text= newsEntity.type
            articleTv.text = newsEntity.title
            articleText.text = "${newsEntity.content}+${newsEntity.description}"
        }

        binding.backImg.setOnClickListener {
            findNavController().popBackStack()
            val bottomNavigationView : BottomNavigationView = activity?.findViewById(R.id.bottom_nav)!!
            bottomNavigationView.visibility = View.VISIBLE
        }

        return binding.root

    }

}