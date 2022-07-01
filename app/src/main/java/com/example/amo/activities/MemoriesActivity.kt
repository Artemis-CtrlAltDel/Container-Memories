package com.example.amo.activities

import android.app.ActionBar
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.BlendMode
import android.graphics.Color
import android.icu.text.DateTimePatternGenerator
import android.opengl.Visibility
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.core.view.marginEnd
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.viewpager2.widget.ViewPager2
import com.example.amo.R
import com.example.amo.adapters.MemoriesAdapter
import com.example.amo.databinding.ActivityMemoriesBinding
import com.example.amo.entities.Memories

class MemoriesActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memories)

        val binding = ActivityMemoriesBinding.inflate(layoutInflater)

        window.statusBarColor = getColor(R.color.transparent)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        binding.apply {
            setContentView(root)
            setSupportActionBar(toolbar)

            /*** BID AS SHIT 1 ***/
            appBar.background = null

            memoriesBack.visibility = View.GONE

            memoriesCreate.background = null
            memoriesBack.background = null

            memoriesCreate.backgroundTintList = baseContext.getColorStateList(R.color.transparent)
            memoriesBack.backgroundTintList = baseContext.getColorStateList(R.color.transparent)

            memoriesCreate.backgroundTintBlendMode = BlendMode.SRC_OVER
            memoriesBack.backgroundTintBlendMode = BlendMode.SRC_OVER

            toolbar.visibility = View.VISIBLE
            /*** BID AS SHIT 1 - ENDS***/

            memoriesBack.setOnClickListener {
                startActivity(Intent(this@MemoriesActivity, MainActivity::class.java))
            }

            toolbar.setNavigationOnClickListener {
                startActivity(Intent(this@MemoriesActivity, MainActivity::class.java))
            }

            nestedScrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (scrollY > pager.getChildAt(0).height/9){
                    memoriesToolbarCard.background = null
                    appBar.background = baseContext.getDrawable(R.drawable.draw_white_gradient_bg_reversed)

                    memoriesBack.visibility = View.VISIBLE

                    memoriesCreate.background = baseContext.getDrawable(R.drawable.draw_fab)
                    memoriesBack.background = baseContext.getDrawable(R.drawable.draw_fab)

                    memoriesCreate.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#d3c14c"))
                    memoriesBack.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#d3c14c"))

                    memoriesCreate.backgroundTintBlendMode = BlendMode.OVERLAY
                    memoriesBack.backgroundTintBlendMode = BlendMode.OVERLAY

                    toolbar.visibility = View.GONE
                }
                else{
                    appBar.background = null

                    memoriesBack.visibility = View.GONE

                    memoriesCreate.background = null
                    memoriesBack.background = null

                    memoriesCreate.backgroundTintList = baseContext.getColorStateList(R.color.transparent)
                    memoriesBack.backgroundTintList = baseContext.getColorStateList(R.color.transparent)

                    memoriesCreate.backgroundTintBlendMode = BlendMode.SRC_OVER
                    memoriesBack.backgroundTintBlendMode = BlendMode.SRC_OVER

                    toolbar.visibility = View.VISIBLE
                }
            }

            val memory_likes1 = 0
            val memory_likes2 = 56511
            val memory_likes3 = 9965

            val memory_reposts = 6018

            val memoriesList : ArrayList<Memories> = arrayListOf(
                Memories(
                    R.drawable.collection_1, R.drawable.pfp1, "Sunflower_2022", memory_likes1, memory_reposts, 65153, baseContext.getString(R.string.string_random_text))
                ,Memories(
                    R.drawable.collection_2, R.drawable.pfp1, "Sunflower_2022", memory_likes2, memory_reposts, 65153, baseContext.getString(R.string.string_random_text))
                ,Memories(
                    R.drawable.collection_3, R.drawable.pfp1, "Sunflower_2022", memory_likes3, memory_reposts, 65153, baseContext.getString(R.string.string_random_text))
                ,Memories(
                    R.drawable.collection_4, R.drawable.pfp1, "Sunflower_2022", memory_likes1, memory_reposts, 65153, baseContext.getString(R.string.string_random_text))
                ,Memories(
                    R.drawable.collection_5, R.drawable.pfp1, "Sunflower_2022", memory_likes2, memory_reposts, 65153, baseContext.getString(R.string.string_random_text))
                ,Memories(
                    R.drawable.collection_6, R.drawable.pfp1, "Sunflower_2022", memory_likes3, memory_reposts, 65153, baseContext.getString(R.string.string_random_text))
                ,Memories(
                    R.drawable.collection_7, R.drawable.pfp1, "Sunflower_2022", memory_likes1, memory_reposts, 65153, baseContext.getString(R.string.string_random_text))
                ,Memories(
                    R.drawable.collection_8, R.drawable.pfp1, "Sunflower_2022", memory_likes2, memory_reposts, 65153, baseContext.getString(R.string.string_random_text))
                ,Memories(
                    R.drawable.collection_9, R.drawable.pfp1, "Sunflower_2022", memory_likes3, memory_reposts, 65153, baseContext.getString(R.string.string_random_text))
                )

            pager.adapter = MemoriesAdapter(memoriesList)
            pager.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.HORIZONTAL, false)
            pager.setHasFixedSize(true)

            var snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(pager)
        }
    }
}