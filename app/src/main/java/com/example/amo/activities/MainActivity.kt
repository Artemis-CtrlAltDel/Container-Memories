package com.example.amo.activities

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.view.children
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.amo.adapters.MainAdapter
import com.example.amo.R
import com.example.amo.entities.Thumbnail
import com.example.amo.databinding.ActivityMainBinding
import com.example.amo.fragments.AddPostFragment
import com.example.amo.fragments.ExploreFragment
import com.example.amo.fragments.HomeFragment
import com.example.amo.fragments.ProfileFragment
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private val scaleX = 0.0f
    private val scaleY = 0.0f
    private var click = 1

    private lateinit var username: String
    private lateinit var email: String
    private lateinit var password: String

    lateinit var fragment: Fragment

    private val homeFragment = HomeFragment()
    private val exploreFragment = ExploreFragment()
    private val profileFragment = ProfileFragment()
    private val addPostFragment = AddPostFragment()

    private lateinit var fm: FragmentManager
    private lateinit var transaction: FragmentTransaction

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

//        val animator = ValueAnimator()
//        animator.setIntValues(1,4,5,2,3,9)
//        animator.duration = 4000
//        animator.addUpdateListener {
//            binding.toolbar.setTitle("title${it.animatedValue}")
//        }

        window.statusBarColor = getColor(R.color.white)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        binding.apply {

            val sp = getSharedPreferences("user_data", MODE_PRIVATE)
            if (sp.getString("token", null).isNullOrBlank()) {
                intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
            }

            /** Volley : 0 **/
            // Retrieve current user's profile.
            /** Volley : 1 **/

            setContentView(root)
            setSupportActionBar(toolbar)

            fm = supportFragmentManager

            /** init home fragment : 0 **/
            fragment = homeFragment

            transaction = fm.beginTransaction()
            transaction.replace(R.id.fragment_content, fragment)
            transaction.commit()

            (bottomAppBarHome.getChildAt(0) as ImageView)
                .imageTintList = ColorStateList.valueOf(getColor(R.color.color_icon_active))

            ((bottomAppBarHome.getChildAt(1)) as View).visibility = View.VISIBLE

            var currentLinearLayoutId = bottomAppBarHome.id

            bottomAppBarSet.children.forEach {
                if (it.id != currentLinearLayoutId) {
                    (((it as LinearLayout).getChildAt(0)) as ImageView)
                        .imageTintList =
                        ColorStateList.valueOf(getColor(R.color.color_icon_inactive))

                    ((it.getChildAt(1)) as View).visibility = View.INVISIBLE
                }
            }
            /** init home fragment : 1 **/

            nestedScrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (scrollY > oldScrollY + 1) {
                    appBar.backgroundTintList = ColorStateList.valueOf(getColor(R.color.color_toolbar_bg))
                    toolbar.title = "Mohamed Bë"
                    toolbar.subtitle = "@RanPo69"
                    toolbar.setTitleTextColor(getColor(R.color.white))
                    toolbar.setSubtitleTextColor(getColor(R.color.color_text_complement_2))
                }
                if (scrollY == 0) {
                    appBar.backgroundTintList = ColorStateList.valueOf(getColor(R.color.transparent))
                    toolbar.title = ""
                    toolbar.subtitle = ""
                }
            }

            toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
//                    R.id.search -> {
//                        Toast.makeText(baseContext, "Coming soon", Toast.LENGTH_SHORT).show()
//                        true
//                    }
//                    R.id.notifications -> {
//                        Toast.makeText(baseContext, "Coming soon", Toast.LENGTH_SHORT).show()
//                        true
//                    }
                    R.id.post_create -> {
//                        Toast.makeText(baseContext, "Coming soon", Toast.LENGTH_SHORT).show()
                        transaction = fm.beginTransaction()
                        transaction.replace(R.id.fragment_content, addPostFragment).commit()
                        toolbar.visibility = View.INVISIBLE
                        bottomAppBar.visibility = View.INVISIBLE
                        true
                    }
                    else -> false
                }
            }
            toolbar.setNavigationOnClickListener {
                Toast.makeText(baseContext, "Coming soon", Toast.LENGTH_SHORT).show()
            }

//            val materialShapeDrawable = bottomAppBar.background as MaterialShapeDrawable
//            materialShapeDrawable.shapeAppearanceModel = materialShapeDrawable.shapeAppearanceModel
//                .toBuilder()
//                .setAllCorners(CornerFamily.ROUNDED, 80f)
//                .build()



            listOf(
                bottomAppBarHome,
                bottomAppBarExplore,
//                bottomAppBarProfile
            ).forEach {

                it.setOnClickListener {
                    fragment = when (it.id) {
                        R.id.bottom_app_bar_home -> homeFragment
                        R.id.bottom_app_bar_explore -> exploreFragment
                        else -> profileFragment
                    }

                    transaction = fm.beginTransaction()
                    transaction.replace(R.id.fragment_content, fragment).commit()

                    (((it as LinearLayout).getChildAt(0)) as ImageView)
                        .imageTintList = ColorStateList.valueOf(getColor(R.color.color_icon_active))

                    ((it.getChildAt(1)) as View).visibility = View.VISIBLE

                    var currentLinearLayoutId = it.id

                    bottomAppBarSet.children.forEach {
                        if (it.id != currentLinearLayoutId) {
                            (((it as LinearLayout).getChildAt(0)) as ImageView)
                                .imageTintList =
                                ColorStateList.valueOf(getColor(R.color.color_icon_inactive))

                            ((it.getChildAt(1)) as View).visibility = View.INVISIBLE
                        }
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

}