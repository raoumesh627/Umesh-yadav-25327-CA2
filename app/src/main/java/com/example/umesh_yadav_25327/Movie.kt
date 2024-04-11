package com.example.umesh_yadav_25327

data class Movie(
    val name: String,
    val image: String,
    val certification: String,
    val description: String,
    val starring: ArrayList<String>,
    val runnning_time_mins: Int,
    var seats_remaining: Int,
    var seats_selected: Int
)
