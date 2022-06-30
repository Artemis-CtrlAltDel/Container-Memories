package com.example.amo.activities

import android.util.Base64
import android.text.TextUtils
import android.util.Patterns
import java.lang.Exception

fun String.decode(): String {
     return Base64.decode(this, Base64.DEFAULT).toString(charset("UTF-8"))
}

fun String.fetch(keyword:String): Pair<Boolean, String>{
     val pattern = "\"${keyword}\":\"?(.+)\"?"

     try {
          val res = Regex(pattern).findAll(this).toList()
          if (res.size == 0)
               return Pair(false, "Could not find the appropriate $keyword")
          else
               return Pair(true, res[0].value.
                    replace("\"", "").
                    split(",")[0].
                    split(":")[1])
     }catch (e : Exception){
          return Pair(false, "$e")
     }

}

fun String.is_valid_email(): Boolean {
     return if (TextUtils.isEmpty(this)) {
          false
     } else {
          Patterns.EMAIL_ADDRESS.matcher(this).matches()
     }
}





































