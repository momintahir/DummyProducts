package com.example.dummyproducts.api

import com.example.dummyproducts.models.ProductsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ProductsApi {
    @GET("/products")
    suspend fun getAllProducts(): ProductsResponse

}