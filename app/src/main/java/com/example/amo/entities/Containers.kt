package com.example.amo.entities

data class Containers(var userImage : Int,
                      var userPfp : Int,
                      var userName : String,
                      var userDescription : String,
                      var userLikes : String? = "",
                      var userImageState : Int? = 0)
