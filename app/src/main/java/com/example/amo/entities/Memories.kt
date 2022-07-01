package com.example.amo.entities

data class Memories(var memoryImage : Int? = null,
                    var memoryPfp : Int? = null,
                    var memoryOwner : String? = null,
                    var memoryLikes : Int = 0,
                    var memoryReposts : Int = 0,
                    var memoryViews : Int = 0,
                    var memoryDescription : String? = "No Description Given")
