package com.example.amo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.amo.R
import com.example.amo.entities.Memories
import com.example.amo.others._ComplexFormatter
import com.example.amo.others.format

class MemoriesAdapter(val itemList : ArrayList<Memories>) :
    RecyclerView.Adapter<MemoriesAdapter.PageHolder>() {

    private val formatter = _ComplexFormatter()

    class PageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var memoryImage : ImageView = itemView.findViewById(R.id.memory_image)
        var memoryPfp : ImageView = itemView.findViewById(R.id.memory_pfp)
        var memory_owner: TextView = itemView.findViewById(R.id.memory_owner)
        var memoryLikes : TextView = itemView.findViewById(R.id.memory_likes)
        var memoryComments : TextView = itemView.findViewById(R.id.memory_comments)
        var memoryViews : TextView = itemView.findViewById(R.id.memory_views)
        var memoryDescription : TextView = itemView.findViewById(R.id.memory_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder {
        return PageHolder(LayoutInflater.from(parent.context).inflate(R.layout.memories_full_card, parent, false))
    }

    override fun onBindViewHolder(holder: PageHolder, position: Int) {

        holder.apply {
            Glide.with(memoryImage.context).load(itemList[position].memoryImage).into(memoryImage)
            Glide.with(memoryImage.context).load(itemList[position].memoryPfp).into(memoryPfp)
            memory_owner.text = itemList[position].memoryOwner.toString()
            memoryLikes.text = "${itemList[position].memoryLikes.format()} Moons"
            memoryComments.text = "${itemList[position].memoryComments.format()} Comments"
            memoryViews.text = "${itemList[position].memoryViews} Views"
            memoryDescription.text = itemList[position].memoryDescription
        }
    }

    override fun getItemCount() = itemList.size
}