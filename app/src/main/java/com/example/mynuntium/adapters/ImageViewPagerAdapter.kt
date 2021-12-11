package com.example.mynuntium.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynuntium.R
import com.example.mynuntium.databinding.ItemImageViewPagerBinding


class ImageViewPagerAdapter:RecyclerView.Adapter<ImageViewPagerAdapter.Vh>() {

    val imageList = arrayListOf(R.drawable.news_three,R.drawable.news_three,R.drawable.news_three)

    inner class Vh(val itemImageViewPagerBinding: ItemImageViewPagerBinding):RecyclerView.ViewHolder(itemImageViewPagerBinding.root){

        fun onBind(image:Int){
            itemImageViewPagerBinding.itemImage.setImageResource(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemImageViewPagerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}