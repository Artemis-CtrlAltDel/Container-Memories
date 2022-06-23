package com.example.amo.activities

import android.content.ContextParams
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.amo.R
import com.example.amo.databinding.ActivitySignupBinding
import org.json.JSONObject

class SignupActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = getColor(R.color.transparent)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val binding = ActivitySignupBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(root)

//            usernameTextBox.requestFocus()

            signupButton.setOnClickListener {

                var username = usernameTextBox.text.toString()
                var email = emailTextBox.text.toString()
                var pass = passTextBox.text.toString()

                var url = "http://192.168.8.102:8080/users/"
                var string_request : StringRequest = object : StringRequest(Request.Method.POST, url,
                    {
                        startActivity(Intent(applicationContext, SigninActivity::class.java))
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
                        params.put("username",username)
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