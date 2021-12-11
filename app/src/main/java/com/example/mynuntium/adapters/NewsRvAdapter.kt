package com.example.mynuntium.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mynuntium.R
import com.example.mynuntium.databinding.BrowseItemRvBinding
import com.example.mynuntium.databse.NewsEntity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class NewsRvAdapter(val listener:OnItemClickListener):ListAdapter<NewsEntity,NewsRvAdapter.Vh>(MyDiffUtil()) {

    inner class Vh(val browseItemRvBinding: BrowseItemRvBinding):RecyclerView.ViewHolder(browseItemRvBinding.root){
        fun onBind(newsEntity: NewsEntity){
            browseItemRvBinding.titleNewsTv.text = newsEntity.title
            browseItemRvBinding.typeNewsTv.text = newsEntity.type
            Picasso.get().load(newsEntity.urlToImage)
                .placeholder(R.drawable.ic_launcher_background)
                .noFade()
                .into(browseItemRvBinding.itemNewsImage,object :Callback{
                override fun onSuccess() {
                    browseItemRvBinding.progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    browseItemRvBinding.progressBar.visibility = View.VISIBLE
                }

            })


            if(newsEntity.isLike){
                browseItemRvBinding.bookmarksImage.setImageResource(R.drawable.ic_bookmarks_selected)
            }else{
                browseItemRvBinding.bookmarksImage.setImageResource(R.drawable.ic_bookmarks_unselected)
            }

            browseItemRvBinding.bookmarksImage.setOnClickListener {
                listener.onItemClick(newsEntity,browseItemRvBinding)
            }

            itemView.setOnClickListener {
                listener.onClick(newsEntity)
            }
        }
    }

    class MyDiffUtil:DiffUtil.ItemCallback<NewsEntity>(){
        override fun areItemsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
            return oldItem==newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(BrowseItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position))
    }

    interface OnItemClickListener{
        fun onItemClick(newsEntity: NewsEntity,browseItemRvBinding: BrowseItemRvBinding)

        fun onClick(newItem: NewsEntity)
    }
}