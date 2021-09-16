package com.example.dummyproducts.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dummyproducts.models.ProductsResponseItem

@Dao
interface ProductsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(product: ProductsResponseItem): Long

    @Query("SELECT * FROM products")
    fun getProductsFromCart(): LiveData<List<ProductsResponseItem>>
}