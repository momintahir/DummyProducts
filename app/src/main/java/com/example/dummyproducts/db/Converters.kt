package com.example.dummyproducts.db

import androidx.room.TypeConverter
import com.example.dummyproducts.models.Rating

class Converters {

    @TypeConverter
    fun fromSource(rating: Rating): String {
        return rating.rate.toString()
    }

    @TypeConverter
    fun toSource(count: Int,rate:Double): Rating {
        return Rating(count, rate)
    }
}