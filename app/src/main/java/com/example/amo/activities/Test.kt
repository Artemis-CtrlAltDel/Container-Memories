package com.example.amo.activities

import android.util.Base64

fun String.addEmoji(): String{
     return "$this😀"
}

fun String.decode(): String {
     return Base64.decode(this, Base64.DEFAULT).toString(charset("UTF-8"))
}

//fun View.fadeOut(){
//    this.animation = null
//}






































