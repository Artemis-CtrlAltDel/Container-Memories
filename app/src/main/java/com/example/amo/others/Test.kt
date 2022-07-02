package com.example.amo.others

import android.util.Base64
import android.text.TextUtils
import android.util.Patterns
import java.lang.Exception
import kotlin.math.floor

fun String.decode() = Base64.decode(this, Base64.DEFAULT).toString(charset("UTF-8"))

fun String.fetch(keyword:String): Pair<Boolean, String>{
     val pattern = "\"${keyword}\":\"?(.+)\"?"

     return try {

          val res = Regex(pattern).findAll(this).toList()
          if (res.size == 0)
               Pair(false, "Could not find the appropriate $keyword")
          else
               Pair(true, res[0].value
                    .replace("\"", "")
                    .split(",")[0]
                    .split(":")[1])

     }catch (e : Exception){
          Pair(false, "$e")
     }

}

fun String.is_valid_email() = if (TextUtils.isEmpty(this)) false
                              else Patterns.EMAIL_ADDRESS.matcher(this).matches()

val formatter = _ComplexFormatter()
fun Int.format() =
     when (this) {
         in 0..999 -> "$this"
         in 1_000L..9_999L -> {
              val res = "${this/1000},${this%1000}"
              "$res"+   if (res.length<5) "0"
              else ""
         }
         else -> "${formatter.format(this.toLong())}"
     }