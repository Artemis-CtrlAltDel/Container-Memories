package com.example.amo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amo.R
import com.example.amo.databinding.FragmentAddPostBinding
import com.example.amo.databinding.FragmentExploreBinding

class AddPostFragment : Fragment() {

    private var _binding : FragmentAddPostBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val context = requireContext()
        val activity = requireActivity()
        _binding = FragmentAddPostBinding.inflate(inflater, container, false)

        binding.apply {
            return root
        }
    }
}