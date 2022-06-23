package com.example.amo.activities

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.RequiresApi
import com.example.amo.R
import com.example.amo.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLoginBinding.inflate(layoutInflater)

        window.statusBarColor = getColor(R.color.transparent)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        binding.apply {
            setContentView(root)



            signinButton.setOnClickListener {
                startActivity(Intent(applicationContext, SigninActivity::class.java))
            }
            signupButton.setOnClickListener {
                startActivity(Intent(applicationContext, SignupActivity::class.java))
            }
        }
    }
}