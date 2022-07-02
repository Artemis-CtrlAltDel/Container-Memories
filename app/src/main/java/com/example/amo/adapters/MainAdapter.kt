package com.example.amo.adapters

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.amo.R
import com.example.amo.activities.ContainersActivity
import com.example.amo.entities.Thumbnail
import com.example.amo.others._ComplexFormatter
import com.example.amo.others.format

class MainAdapter(val activity : Activity, val item : ArrayList<Thumbnail>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    val intent = Intent(activity, ContainersActivity::class.java)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val thumbnailCardView : CardView = itemView.findViewById(R.id.thumbnail_card)
        val thumbnailImageView : ImageView = itemView.findViewById(R.id.thumbnail)
        val thumbnailOwnerTextView : TextView = itemView.findViewById(R.id.thumbnail_owner)
        val thumbnailLikeCountTextView : TextView = itemView.findViewById(R.id.thumbnail_like_count)
        val thumbnailBullshitCardView : CardView = itemView.findViewById(R.id.that_little_shit_in_the_bottom_hhh)
        val thumbnailLikeWrapper : LinearLayout = itemView.findViewById(R.id.thumbnail_like_wrapper)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.containers_preview_card, parent, false))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            Glide.with(thumbnailImageView.context).load(item[position].thumbnail).into(thumbnailImageView)
            thumbnailOwnerTextView.text = item[position].thumbnailOwner
            if (item[position].thumbnailLikes == 0){
                thumbnailLikeWrapper.visibility = View.GONE
            }else{
                thumbnailLikeCountTextView.text = item[position].thumbnailLikes.format()
                thumbnailLikeWrapper.visibility = View.VISIBLE
            }
            thumbnailBullshitCardView.setCardBackgroundColor(ColorStateList.valueOf(thumbnailImageView.context.getColor(item[position].thumbnailColor)))

            thumbnailCardView.setOnClickListener {
                intent.putExtra("intent_source", "MAIN ADAPTER TO CONTAINERS")
                intent.putExtra("thumbnailImage", item[position].thumbnail)
                intent.putExtra("thumbnailOwner", item[position].thumbnailOwner)
                intent.putExtra("thumbnailLikes", item[position].thumbnailLikes)
                thumbnailCardView.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount() = item.size
}