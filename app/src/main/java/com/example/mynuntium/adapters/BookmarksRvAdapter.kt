package com.example.mynuntium.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mynuntium.R
import com.example.mynuntium.databinding.ItemRecommendRvBinding
import com.example.mynuntium.databse.NewsEntity
import com.example.mynuntium.databse.NewsLikeEntity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class BookmarksRvAdapter(val listener:OnItemClickListener):ListAdapter<NewsLikeEntity,BookmarksRvAdapter.Vh>(MyDiffUtil()) {

    inner class Vh(val itemRecommendRvBinding: ItemRecommendRvBinding):RecyclerView.ViewHolder(itemRecommendRvBinding.root){

        fun onBind(newItem: NewsLikeEntity){
            Picasso.get().load(newItem.urlToImage)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemRecommendRvBinding.itemImageView,object :Callback{
                    override fun onSuccess() {
                        itemRecommendRvBinding.progressBar.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        itemRecommendRvBinding.progressBar.visibility = View.VISIBLE
                    }
                })
            itemRecommendRvBinding.itemNewsTypeTv.text = newItem.type
            itemRecommendRvBinding.itemNewsTitleTv.text = newItem.title

            itemView.setOnClickListener {
                listener.onClick(NewsEntity(
                    id = newItem.id,
                    author = newItem.author,
                    content = newItem.content,
                    description = newItem.description,
                    publishedAt = newItem.publishedAt,
                    title = newItem.title,
                    urlToImage = newItem.urlToImage,
                    isLike = newItem.isLike,
                    type = newItem.type
                ))
            }
        }
    }

    class MyDiffUtil:DiffUtil.ItemCallback<NewsLikeEntity>(){
        override fun areItemsTheSame(oldItem: NewsLikeEntity, newItem: NewsLikeEntity): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: NewsLikeEntity, newItem: NewsLikeEntity): Boolean {
            return oldItem==newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRecommendRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position))
    }

    interface OnItemClickListener{
        fun onClick(newsEntity: NewsEntity)
    }
}