package com.example.dummyproducts.api

import com.example.dummyproducts.models.ProductsResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsApi {
    @GET("v2/everything")
    suspend fun getAllProducts(): Call<ProductsResponse>

}