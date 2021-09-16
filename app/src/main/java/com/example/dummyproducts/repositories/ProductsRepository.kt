package com.example.dummyproducts.repositories

import com.example.dummyproducts.api.ProductsApi
import com.example.dummyproducts.db.ProductsDatabase
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val db: ProductsDatabase,
    private val api: ProductsApi
) {
    suspend fun getSearchNews() = api.getAllProducts()
}