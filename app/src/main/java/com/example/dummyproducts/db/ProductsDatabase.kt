package com.example.dummyproducts.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object {
        @Volatile
        private var instance: ProductsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ProductsDatabase::class.java,
                "products_db.db"
            ).build()

    }
}