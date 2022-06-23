package com.example.amo.entities

data class Post(var post_owner : User,
                var post_time : String,
                var post_description : String?,
                var post_keys : String?,
                var post_media : Int?,
                var post_likes : String? = null,
                var post_reposts : String? = null,
                var post_comments : String? = "")
