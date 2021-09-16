package com.example.dummyproducts.repositories

import com.example.dummyproducts.api.ProductsApi
import com.example.dummyproducts.db.ProductsDatabase
import com.example.dummyproducts.models.ProductsResponseItem
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val db: ProductsDatabase,
    private val api: ProductsApi
) {
    suspend fun getAllProducts() = api.getAllProducts()
    suspend fun upsert(article: ProductsResponseItem) = db.getProductsDao().upsert(article)
    fun getProductsFromCart() = db.getProductsDao().getProductsFromCart()

}