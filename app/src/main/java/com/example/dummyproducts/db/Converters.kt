package com.example.dummyproducts.db

import androidx.room.TypeConverter
import com.example.dummyproducts.models.Rating

class Converters {

    @TypeConverter
    fun fromRating(rating: Rating): String {
        return rating.rate.toString()
    }

    @TypeConverter
    fun toRating(rate:Double): Rating {
        return Rating(rate, rate)
    }
}