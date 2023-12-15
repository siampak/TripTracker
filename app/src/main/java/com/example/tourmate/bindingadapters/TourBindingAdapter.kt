package com.example.tourmate.bindingadapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.tourmate.utils.getFormattedDateTime
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.*




@BindingAdapter("app:timestampText")
fun setTimestampText(tv: TextView, createOn: Timestamp) {

    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss a", Locale.getDefault())
    val formattedTimestamp: String = sdf.format(createOn.toDate())
    tv.text = formattedTimestamp

//    tv.text= SimpleDateFormat(createOn.toDate().toString(), "yyyy/MM/dd HH:mm:ss", Locale.getDefault())
//         val formattedTimestamp: String = sdf.format(timestamp.toDate())
//         tv.text =formattedTimestamp
}


@BindingAdapter("app:setWeatherDate")
fun setFormattedWeatherDate(tv: TextView, date: Long){
    val formattedDate = SimpleDateFormat("EEE, dd, yyyy", Locale.getDefault())
        .format(Date(date * 1000))
    tv.text =formattedDate
}



@BindingAdapter("app:setWeatherIcon")
fun setWeatherIcon(iv: ImageView, icon: String?){
    icon?.let {
        val url = "https://openweathermap.org/img/wn/${icon}@2x.png"  //link from https://openweathermap.org/weather-conditions
        Glide.with(iv).load(url).into(iv)  //download image from this url
    }
}


    //for date & Time Format
//    @BindingAdapter("app:setFormattedDate")
//    fun setFormattedDate(tv: TextView, date: Long){
//        tv.text = getFormattedDateTime(date, "dd/MM/yyyy")
//    }

//    @BindingAdapter("app:setFormattedTime")
//    fun setFormattedTime(tv: TextView, time: Long){
//        tv.text = getFormattedDateTime(time, "hh:mm a")
//    }




