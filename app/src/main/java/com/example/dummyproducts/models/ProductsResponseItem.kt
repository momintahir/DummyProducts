package com.example.dummyproducts.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = "products"
)
data class ProductsResponseItem(
    @PrimaryKey
    val id: Int,
    val category: String,
    val description: String,
    val image: String,
    val price: Double,
    val title: String
): Parcelable