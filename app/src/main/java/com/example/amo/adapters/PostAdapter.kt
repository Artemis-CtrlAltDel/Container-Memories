package com.example.amo.adapters

import android.content.res.ColorStateList
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.amo.R
import com.example.amo.entities.Post
import com.example.amo.others._ComplexFormatter
import com.example.amo.others.format

class PostAdapter(val item: ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private lateinit var animation_like : Animation
    private lateinit var animation_repost : Animation

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postOwnerPfp: ImageView = itemView.findViewById(R.id.post_owner_pfp)
        val postOwnerName: TextView = itemView.findViewById(R.id.post_owner_name)
        val postOwnerUsername: TextView = itemView.findViewById(R.id.post_owner_username)
        val postTime: TextView = itemView.findViewById(R.id.post_time)
        val postDesc: TextView = itemView.findViewById(R.id.post_desc)
        val postKeys: TextView = itemView.findViewById(R.id.post_keys)
        val postMedia: ImageView = itemView.findViewById(R.id.post_media)
        val postLikes: TextView = itemView.findViewById(R.id.post_likes)
        val postReposts: TextView = itemView.findViewById(R.id.post_reposts)

        val postLikePackage: LinearLayout = itemView.findViewById(R.id.post_like_package)
        val postRepostPackage: LinearLayout = itemView.findViewById(R.id.post_repost_package)

        val postLikeIcon : ImageView = itemView.findViewById(R.id.post_like_icon)
        val postRepostIcon : ImageView = itemView.findViewById(R.id.post_repost_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.containers_post_card, parent, false)
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {

            var likeClick = 0
            var repostClick = 0
            animation_like = AnimationUtils.loadAnimation(postMedia.context, R.anim.anim_like_pressed)
            animation_repost = AnimationUtils.loadAnimation(postMedia.context, R.anim.anim_repost_pressed)

            postLikePackage.setOnClickListener {
                if (likeClick == 0) {

                    (((it as LinearLayout).children.elementAt(0)) as ImageView).startAnimation(animation_like)

                    (((it as LinearLayout).children.elementAt(0)) as ImageView).setImageDrawable(
                        postMedia.context.getDrawable(R.drawable.ic_like)
                    )
                    (((it as LinearLayout).children.elementAt(0)) as ImageView).imageTintList =
                        ColorStateList.valueOf(postMedia.context.getColor(R.color.color_harmony_1))
                    (((it as LinearLayout).children.elementAt(1)) as TextView).setTextColor(
                        postMedia.context.getColor(R.color.color_harmony_1_0)
                    )

                    likeClick = 1
                } else {
                    (((it as LinearLayout).children.elementAt(0)) as ImageView).setImageDrawable(
                        postMedia.context.getDrawable(R.drawable.ic_like_explore)
                    )
                    (((it as LinearLayout).children.elementAt(0)) as ImageView).imageTintList =
                        ColorStateList.valueOf(postMedia.context.getColor(R.color.white))
                    (((it as LinearLayout).children.elementAt(1)) as TextView).setTextColor(
                        postMedia.context.getColor(R.color.white)
                    )

                    likeClick = 0
                }
            }

            postRepostPackage.setOnClickListener {
                if (repostClick == 0) {
                    (((it as LinearLayout).children.elementAt(0)) as ImageView).imageTintList =
                        ColorStateList.valueOf(postMedia.context.getColor(R.color.color_tri_harmony_1))
                    (((it as LinearLayout).children.elementAt(1)) as TextView).setTextColor(
                        postMedia.context.getColor(R.color.color_tri_harmony_1_0)
                    )

                    repostClick = 1
                } else {
                    (((it as LinearLayout).children.elementAt(0)) as ImageView).imageTintList =
                        ColorStateList.valueOf(postMedia.context.getColor(R.color.white))
                    (((it as LinearLayout).children.elementAt(1)) as TextView).setTextColor(
                        postMedia.context.getColor(R.color.white)
                    )

                    repostClick = 0
                }
            }

            Glide.with(postMedia.context).load(item[position].post_owner.pfp).into(postOwnerPfp)
            if (postMedia != null)
                Glide.with(postMedia.context).load(item[position].post_media).into(postMedia)
            postOwnerName.text = item[position].post_owner.name
            postOwnerUsername.text = item[position].post_owner.username
            postTime.text = item[position].post_time
            postDesc.text = item[position].post_description
            postKeys.text = item[position].post_keys
            postLikes.text = item[position].post_likes.format()
            postReposts.text = item[position].post_reposts.format()

        }
    }

    override fun getItemCount() = item.size
}