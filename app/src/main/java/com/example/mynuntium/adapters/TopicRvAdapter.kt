package com.example.mynuntium.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynuntium.R
import com.example.mynuntium.databinding.ItemTopicBinding

class TopicRvAdapter(val listener:OnItemClickListener):RecyclerView.Adapter<TopicRvAdapter.Vh>() {

    val topicNameList = arrayListOf(
        "Sports",
        "Politics",
        "Life",
        "Gaming",
        "Animals",
        "Nature",
        "Food",
        "Art",
        "History",
        "Fashion"
    )
    val topicImageList = arrayListOf(
        R.drawable.rugby_ball,
        R.drawable.politcs,
        R.drawable.live,
        R.drawable.gaming,
        R.drawable.animals,
        R.drawable.nature,
        R.drawable.food,
        R.drawable.art,
        R.drawable.history,
        R.drawable.fashion
    )

    inner class Vh(val itemTopicBinding: ItemTopicBinding):RecyclerView.ViewHolder(itemTopicBinding.root){

        fun onBind(position: Int){
            itemTopicBinding.apply {
                topicImage.setImageResource(topicImageList[position])
                topicName.text = topicNameList[position]
            }

            itemView.setOnClickListener {
                listener.onItemClick(position, itemTopicBinding)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemTopicBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return topicNameList.size

    }

    interface OnItemClickListener{
        fun onItemClick(position: Int,itemTopicBinding: ItemTopicBinding)
    }
}