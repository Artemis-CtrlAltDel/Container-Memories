package com.example.amo.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.amo.R
import com.example.amo.activities.MainActivity
import com.example.amo.activities.MemoriesActivity
import com.example.amo.databinding.FragmentAddPostBinding
import com.example.amo.databinding.FragmentExploreBinding

class AddPostFragment : Fragment() {

    private var _binding : FragmentAddPostBinding? = null
    private val binding get() = _binding!!

    private val pickImage = 100
    private var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val context = requireContext()
        val activity = requireActivity()
        _binding = FragmentAddPostBinding.inflate(inflater, container, false)

        binding.apply {
            postCancel.setOnClickListener {
                startActivity(Intent(context, MainActivity::class.java))

                activity.finishAffinity()
            }

            postMediaPackage.setOnClickListener {
                val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                startActivityForResult(gallery, pickImage)
            }

            postSubmit.setOnClickListener {
                startActivity(Intent(context, MemoriesActivity::class.java))

                activity.finishAffinity()
            }
            return root
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            binding.postMedia.setImageURI(imageUri)
            binding.postMediaUpload.visibility = View.INVISIBLE
            binding.postMediaPackage.backgroundTintList = ColorStateList.valueOf(requireContext().getColor(R.color.transparent))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}