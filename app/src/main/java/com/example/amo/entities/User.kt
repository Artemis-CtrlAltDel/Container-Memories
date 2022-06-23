package com.example.amo.entities

data class User(var pfp : Int?,
                var name : String,
                var username : String,
                var email : String? = "",
                var pass : String? = "")
