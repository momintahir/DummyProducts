package com.example.dummyproducts.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dummyproducts.models.ProductsResponseItem

@Database(
    entities = [ProductsResponseItem::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class ProductsDatabase : RoomDatabase() {
    abstract fun getProductsDao(): ProductsDao

}