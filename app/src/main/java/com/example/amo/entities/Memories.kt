package com.example.amo.entities

data class Memories(var memoryImage : Int? = null,
                    var memoryPfp : Int? = null,
                    var memoryOwner : String? = null,
                    var memoryLikes : String? = "0",
                    var memoryComments : String? = "0",
                    var memoryViews : String? = "0",
                    var memoryDescription : String? = "No Description Given")
