package com.example.mynuntium.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynuntium.R
import com.example.mynuntium.databinding.ItemRecommendRvBinding
import com.example.mynuntium.databse.NewsEntity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class RecommendRvAdapter(val boolean: Boolean,val newEntityList:ArrayList<NewsEntity>,val listener:OnItemClickListener):RecyclerView.Adapter<RecommendRvAdapter.Vh>() {

    inner class Vh(val itemRecommendRvBinding: ItemRecommendRvBinding):RecyclerView.ViewHolder(itemRecommendRvBinding.root){

        fun onBind(newsEntity: NewsEntity){
            itemRecommendRvBinding.itemNewsTitleTv.text = newsEntity.title
            itemRecommendRvBinding.itemNewsTypeTv.text = newsEntity.type
            Picasso.get().load(newsEntity.urlToImage)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemRecommendRvBinding.itemImageView,object : Callback {
                    override fun onSuccess() {
                        itemRecommendRvBinding.progressBar.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        itemRecommendRvBinding.progressBar.visibility = View.VISIBLE
                    }
                })

            itemView.setOnClickListener {
                listener.onItemClick(newsEntity)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
         return Vh(ItemRecommendRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(newEntityList[position])
    }

    override fun getItemCount(): Int {
        return if(boolean){
            4
        }else{
            newEntityList.size
        }
    }

    interface OnItemClickListener{
        fun onItemClick(newsEntity: NewsEntity)
    }

}