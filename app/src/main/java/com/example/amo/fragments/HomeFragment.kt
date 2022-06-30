package com.example.amo.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.amo.R
import com.example.amo.activities.ContainersActivity
import com.example.amo.activities.MemoriesActivity
import com.example.amo.adapters.MainAdapter
import com.example.amo.databinding.FragmentHomeBinding
import com.example.amo.entities.Thumbnail

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var list : ArrayList<Thumbnail>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreate(savedInstanceState)

        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.apply {
            val context = requireContext()
            val activity = requireActivity()

            Glide.with(context).load(R.drawable.collection_1).into(collection1)
            Glide.with(context).load(R.drawable.collection_2).into(collection2)
            Glide.with(context).load(R.drawable.collection_3).into(collection3)

            //val bitmap4 = BitmapFactory.decodeResource(resources, R.drawable.collection_4).reconfigure(300, 300, Bitmap.Config.ARGB_8888)
            //val bitmap5 = BitmapFactory.decodeResource(resources, R.drawable.collection_5).reconfigure(300, 300, Bitmap.Config.ARGB_8888)
            //val bitmap6 = BitmapFactory.decodeResource(resources, R.drawable.collection_6).reconfigure(300, 300, Bitmap.Config.ARGB_8888)

            val thumbnailTitle1 = "Username_1"
            val thumbnailTitle2 = "Username_2"
            val thumbnailTitle3 = "hada_shi_zaml_lidar_had_username"

            val thumbnailLikes1 = 0
            val thumbnailLikes2 = 1520
            val thumbnailLikes3 = 37373

            val thumbnail1 = Thumbnail(
                R.drawable.collection_1, thumbnailTitle1, thumbnailLikes1, R.color.color_random_bullshit_9)
            val thumbnail2 = Thumbnail(
                R.drawable.collection_2, thumbnailTitle2, thumbnailLikes2, R.color.color_random_bullshit_8)
            val thumbnail3 = Thumbnail(
                R.drawable.collection_3, thumbnailTitle3, thumbnailLikes3, R.color.color_random_bullshit_7)
            val thumbnail4 = Thumbnail(
                R.drawable.collection_4, thumbnailTitle1, thumbnailLikes1, R.color.color_random_bullshit_6)
            val thumbnail5 = Thumbnail(
                R.drawable.collection_5, thumbnailTitle2, thumbnailLikes2, R.color.color_random_bullshit_5)
            val thumbnail6 = Thumbnail(
                R.drawable.collection_6, thumbnailTitle3, thumbnailLikes3, R.color.color_random_bullshit_4)
            val thumbnail7 = Thumbnail(
                R.drawable.collection_7, thumbnailTitle1, thumbnailLikes1, R.color.color_random_bullshit_3)
            val thumbnail8 = Thumbnail(
                R.drawable.collection_8, thumbnailTitle2, thumbnailLikes2, R.color.color_random_bullshit_2)
            val thumbnail9 = Thumbnail(
                R.drawable.collection_9, thumbnailTitle3, thumbnailLikes3, R.color.color_random_bullshit_1)

            list = arrayListOf(
                thumbnail1,
                thumbnail2,
                thumbnail3,
                thumbnail4,
                thumbnail5,
                thumbnail6,
                thumbnail7,
                thumbnail8,
                thumbnail9
            )

            var intent : Intent

            val adapter = MainAdapter(activity, list)
            recycler.adapter = adapter
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            yourLatestContainers.setOnClickListener {
                intent = Intent(activity, MemoriesActivity::class.java)
                intent.putExtra("intent_source", "MAIN ACTIVITY TO MEMORIES")
                startActivity(intent)
            }

            seeAllContainers.setOnClickListener {
                intent = Intent(activity, ContainersActivity::class.java)
                intent.putExtra("intent_source", "MAIN ACTIVITY TO CONTAINERS")
                startActivity(intent)
            }

            return root
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}