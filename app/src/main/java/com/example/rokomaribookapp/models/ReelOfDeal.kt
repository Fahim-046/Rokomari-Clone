package com.example.rokomaribookapp.models

data class ReelOfDeal(
    val id: Int,
    val bookName: String,
    val authorName: String,
    val price: Int,
    var isViewed: Boolean
)
