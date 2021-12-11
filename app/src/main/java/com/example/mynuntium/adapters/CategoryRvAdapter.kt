package com.example.mynuntium.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynuntium.R
import com.example.mynuntium.databinding.CategoryItemRvBinding
import com.example.mynuntium.databinding.ItemTopicBinding

class CategoryRvAdapter(val listener:OnItemClickListener):RecyclerView.Adapter<CategoryRvAdapter.Vh>() {

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
        "Fashion",
        "Covid-19",
        "Middle East"
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
        R.drawable.fashion,
        R.drawable.covid,
        R.drawable.middle_east
    )

    inner class Vh(val categoryItemRvBinding: CategoryItemRvBinding):RecyclerView.ViewHolder(categoryItemRvBinding.root){

        fun onBind(position: Int){
            categoryItemRvBinding.apply {
                topicImage.setImageResource(topicImageList[position])
                topicName.text = topicNameList[position]
            }

            itemView.setOnClickListener {
                listener.onItemClick(position, categoryItemRvBinding)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(CategoryItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return topicNameList.size

    }

    interface OnItemClickListener{
        fun onItemClick(position: Int,categoryItemRvBinding: CategoryItemRvBinding)
    }
}