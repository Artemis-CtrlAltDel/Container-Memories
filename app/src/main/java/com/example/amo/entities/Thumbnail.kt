package com.example.amo.entities

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

data class Thumbnail(var thumbnail: Int,
                     var thumbnailOwner: String,
                     var thumbnailLikes: Int = 0,
                     var thumbnailColor: Int)
