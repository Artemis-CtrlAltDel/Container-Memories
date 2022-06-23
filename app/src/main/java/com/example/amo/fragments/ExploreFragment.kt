package com.example.amo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.amo.R
import com.example.amo.adapters.PostAdapter
import com.example.amo.databinding.FragmentExploreBinding
import com.example.amo.databinding.FragmentHomeBinding
import com.example.amo.entities.Post
import com.example.amo.entities.User

class ExploreFragment : Fragment() {

    private var _binding : FragmentExploreBinding? = null
    private val binding get() = _binding!!

    lateinit var users : ArrayList<User>
    lateinit var posts : ArrayList<Post>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreate(savedInstanceState)

        // Inflate the layout for this fragment
        val context = requireContext()
        val activity = requireActivity()
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        binding.apply {

            users = arrayListOf(
                User(R.drawable.pfp, "Fname Lname 1", "@Someone1"),
                User(R.drawable.pfp1, "Fname Lname 2", "@Someone2"),
                User(R.drawable.pfp2, "Mohamed Bë", "@Someone3"),
                )

            val post1 = Post(
                users[0], "59m", "first post !","#first #containers #Moons",
                R.drawable.collection_9, 205564, 1900
            )
            val post2 = Post(
                users[1], "20m", "second post !","#second #containers #Moons",
                R.drawable.collection_6, 5656, 607
            )
            val post3 = Post(
                users[2], "5s", "last post !","#last #containers #Moons",
                R.drawable.collection_10, 184, 30
            )

            posts = arrayListOf(
                post1,
                post2,
                post3
            )

            val adapter = PostAdapter(posts)

            postRecycler.adapter = adapter
            postRecycler.setHasFixedSize(true)
            postRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)

            return root
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}