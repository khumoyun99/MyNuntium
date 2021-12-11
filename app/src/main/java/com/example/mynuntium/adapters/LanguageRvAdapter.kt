package com.example.mynuntium.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mynuntium.databinding.LanguageItemBinding

class LanguageRvAdapter(val listener:OnItemClickListener):RecyclerView.Adapter<LanguageRvAdapter.MyViewHolder>() {

    val languageList = arrayListOf("English","Turkish","German","Spanish")
    var rowIndex = 0

    inner class MyViewHolder(val languageItemBinding: LanguageItemBinding):RecyclerView.ViewHolder(languageItemBinding.root){
        lateinit var relativeLayout : RelativeLayout
        lateinit var textView:TextView
        lateinit var imageView:ImageView
        fun onBind(item:String,position: Int){

            languageItemBinding.englishTv.text = item
            relativeLayout = languageItemBinding.languageLl
            textView = languageItemBinding.englishTv
            imageView = languageItemBinding.selectBtn



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LanguageItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(languageList[position],position)
        holder.relativeLayout.setOnClickListener {
            rowIndex=position
            listener.onItemClick(languageList[position])
            notifyDataSetChanged()
        }
        if(rowIndex==position){
            holder.relativeLayout.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor("#475AD7"))
            holder.textView.setTextColor(Color.WHITE)
            holder.imageView.visibility = View.VISIBLE
        }else{
            holder.relativeLayout.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor("#F3F4F6"))
            holder.textView.setTextColor(Color.parseColor("#666C8E"))
            holder.imageView.visibility = View.INVISIBLE
        }

    }

    override fun getItemCount(): Int {
        return languageList.size
    }
    interface OnItemClickListener{
        fun onItemClick(item: String)
    }
}