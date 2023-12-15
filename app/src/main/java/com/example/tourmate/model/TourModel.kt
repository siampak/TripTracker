package com.example.tourmate.model

import com.google.firebase.Timestamp


data class TourModel(
    var id:String? = null,
    var userId:String? = null,
    var title:String? = null,
    var destination:String? = null,
    var budget: Int? = null,
    var createOn:Timestamp= Timestamp.now(),
    var isCompleted: Boolean = false
//    var date: Long,
//    var time: Long

    )


