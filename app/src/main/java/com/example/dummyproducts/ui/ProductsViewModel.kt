package com.example.dummyproducts.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dummyproducts.models.ProductsResponse
import com.example.dummyproducts.models.ProductsResponseItem
import com.example.dummyproducts.repositories.ProductsRepository
import com.example.dummyproducts.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    val products: MutableStateFlow<Resource<ProductsResponse>> = MutableStateFlow(Resource.Empty())

    fun getAllProducts() = viewModelScope.launch {
        products.value = Resource.Loading()
        val response = productsRepository.getAllProducts()
        Log.d("My Response",response.toString())
        products.value = Resource.Success(response)

    }

    fun saveProduct(product: ProductsResponseItem)=viewModelScope.launch {
        productsRepository.upsert(product)
    }

    fun getProductsFromCart()=productsRepository.getProductsFromCart()


}