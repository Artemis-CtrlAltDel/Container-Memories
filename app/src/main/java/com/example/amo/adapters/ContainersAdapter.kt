package com.example.amo.adapters

import android.content.res.ColorStateList
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.amo.R
import com.example.amo.entities.Containers
import com.example.amo.others._ComplexFormatter
import kotlin.collections.ArrayList

class ContainersAdapter(val itemList : ArrayList<Containers>) : RecyclerView.Adapter<ContainersAdapter.ViewHolder>() {

    private val likesFormatter = _ComplexFormatter()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userImage : ImageView = itemView.findViewById(R.id.user_image)
        val userPfp : ImageView = itemView.findViewById(R.id.user_pfp)
        val userName : TextView = itemView.findViewById(R.id.user_name)
        val userDescription : TextView = itemView.findViewById(R.id.user_description)
        val userLikes : TextView = itemView.findViewById(R.id.user_likes)
        val moonIt : ImageView = itemView.findViewById(R.id.moon_it)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.containers_full_card, parent, false))
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.apply {
            Glide.with(userImage.context).load(itemList[position].userImage).into(userImage)
            Glide.with(userImage.context).load(itemList[position].userPfp).into(userPfp)
            userName.text = itemList[position].userName
            userDescription.text = itemList[position].userDescription
            userLikes.text = if (itemList[position].userLikes == "0" || itemList[position].userLikes.isNullOrEmpty()){ ""
                            }else{ likesFormatter.format(itemList[position].userLikes.toString().toLong()) }
            moonIt.imageTintList =  if (itemList[position].userImageState == 0){ ColorStateList.valueOf(userImage.context.getColor(R.color.white))
                                    }else{ ColorStateList.valueOf(userImage.context.getColor(R.color.color_harmony_0)) }

            moonIt.setOnClickListener {
                if (itemList[position].userImageState == 1){
                    itemList[position].userImageState = 0
                    itemList[position].userLikes = (itemList[position].userLikes.toString().toLong() - 1).toString()
                    notifyDataSetChanged()
                }else{
                    itemList[position].userImageState = 1
                    itemList[position].userLikes = if (itemList[position].userLikes.isNullOrEmpty()){ "1" }else{ (itemList[position].userLikes.toString().toLong() + 1).toString() }
                    notifyDataSetChanged()
                }
            }
            userImage.setOnClickListener(object : DoubleClickListener(){
                override fun onDoubleClick(v: View?) {
                    if (itemList[position].userImageState == 1){
                        itemList[position].userImageState = 0
                        itemList[position].userLikes = (itemList[position].userLikes.toString().toLong() - 1).toString()
                        notifyDataSetChanged()
                    }else{
                        itemList[position].userImageState = 1
                        itemList[position].userLikes = if (itemList[position].userLikes.isNullOrEmpty()){ "1" }else{ (itemList[position].userLikes.toString().toLong() + 1).toString() }
                        notifyDataSetChanged()
                    }
                }

            })
        }
    }

    override fun getItemCount() = itemList.size

    abstract class DoubleClickListener : View.OnClickListener {
        var lastClickTime: Long = 0
        override fun onClick(v: View?) {
            val clickTime = System.currentTimeMillis()
            if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
                onDoubleClick(v)
            }
            lastClickTime = clickTime
        }

        abstract fun onDoubleClick(v: View?)

        companion object {
            private const val DOUBLE_CLICK_TIME_DELTA: Long = 300 //milliseconds
        }
    }


}