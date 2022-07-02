package com.example.amo.entities

data class Memories(var memory_image : Int? = null,
                    var memory_pfp : Int? = null,
                    var memory_owner : String? = null,
                    var memory_likes : Int = 0,
                    var memory_reposts : Int = 0,
                    var memory_views : Int = 0,
                    var memory_tags : ArrayList<String> = arrayListOf(),
                    var memory_description : String? = "No Description Given")
