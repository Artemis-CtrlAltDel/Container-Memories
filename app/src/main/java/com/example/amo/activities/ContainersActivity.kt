package com.example.amo.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.amo.R
import com.example.amo.adapters.ContainersAdapter
import com.example.amo.databinding.ActivityContainersBinding
import com.example.amo.databinding.ActivityMainBinding
import com.example.amo.databinding.ContainersFullCardBinding
import com.example.amo.entities.Containers

class ContainersActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val binding = ActivityContainersBinding.inflate(layoutInflater)

        window.statusBarColor = getColor(R.color.transparent)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        binding.apply {
            setContentView(root)
            setSupportActionBar(toolbar)

            toolbar.setNavigationOnClickListener {
                startActivity(Intent(this@ContainersActivity, MainActivity::class.java))
            }

            var containersList : ArrayList<Containers> = arrayListOf()

            if (intent.getStringExtra("intent_source") == "MAIN ADAPTER TO CONTAINERS"){
                containersList.add(Containers(
                    intent.getIntExtra("thumbnailImage", R.drawable.collection_1),
                    R.drawable.pfp,
                    intent.getStringExtra("thumbnailOwner")!!.replace("@", ""),
                    "",
                    intent.getIntExtra("thumbnailLikes", 0)))
            }

            containersList.add(Containers(R.drawable.collection_1, R.drawable.pfp, "Username 1",  "Allo what the shit 1", 321561691, 1))
            containersList.add(Containers(R.drawable.collection_2, R.drawable.pfp1, "Username 2",  "Allo what the shit 2", 0, 0))
            containersList.add(Containers(R.drawable.collection_3, R.drawable.pfp2, "Username 3",  "Allo what the shit 3", 2, 1))

            val adapter = ContainersAdapter(containersList)
            recycler.adapter = adapter
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.HORIZONTAL, false)

            var snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(recycler)

        }
    }
}