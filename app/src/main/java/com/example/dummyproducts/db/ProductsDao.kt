package com.example.dummyproducts.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dummyproducts.models.ProductsResponseItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(product: ProductsResponseItem): Long

    @Query("SELECT * FROM pro")
    fun getAllArticles(): Flow<List<ProductsResponseItem>>
}