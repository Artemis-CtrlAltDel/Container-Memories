package com.example.amo.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.amo.R
import com.example.amo.databinding.ActivitySigninBinding
import org.json.JSONObject

class SigninActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = getColor(R.color.transparent)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val binding = ActivitySigninBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(root)

            val sp = getSharedPreferences("user_data", MODE_PRIVATE)
            val editor = sp.edit()

//            emailTextBox.requestFocus()

            signinButton.setOnClickListener {

                var email = emailTextBox.text.toString()
                var pass = passTextBox.text.toString()

                var url = "http://192.168.8.104:8080/authenticate/"
                var string_request : StringRequest = object : StringRequest(
                    Request.Method.POST, url,
                    {
                        val json = JSONObject(it)
//                        json.getString("token")

                        editor.putString("token", json.getString("token"))
                        editor.apply()

                        startActivity(Intent(applicationContext, MainActivity::class.java))
                        finishAffinity()
                    },
                    {
                        Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
//                        Log.e("FAILED message : ", "${it.message}")
//                        Log.e("FAILED stack trace : ", "${it.printStackTrace()}" )
                    }
                )
                {
                    override fun getBodyContentType(): String {
                        return "application/json"
                    }

                    @Throws(AuthFailureError::class)
                    override fun getBody(): ByteArray {
                        val params = HashMap<String, String>()
                        params.put("email",email)
                        params.put("pass", pass)
                        return JSONObject(params as Map<String, String>).toString().toByteArray()
                    }
                }

                Volley.newRequestQueue(applicationContext).add(string_request)
            }
        }
    }
}