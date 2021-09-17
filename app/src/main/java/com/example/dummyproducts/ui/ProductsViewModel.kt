package com.example.dummyproducts.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dummyproducts.ProductsApplication
import com.example.dummyproducts.models.ProductsResponse
import com.example.dummyproducts.models.ProductsResponseItem
import com.example.dummyproducts.repositories.ProductsRepository
import com.example.dummyproducts.util.Resource
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
    app: Application
) : AndroidViewModel(app) {

    val products: MutableStateFlow<Resource<ProductsResponse>> = MutableStateFlow(Resource.Empty())

    fun getAllProducts() = viewModelScope.launch {
        products.value = Resource.Loading()
        try {
            if (hasInternetConnection()) {
                val response = productsRepository.getAllProducts()
                products.value = Resource.Success(response)
            } else {
                products.value = Resource.Error("No internet connection")

            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> products.value = Resource.Error("Network Failure")
                else -> products.value = Resource.Error("Conversion Error")
            }
        }


    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager =
            getApplication<ProductsApplication>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true

                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true

                    else -> false
                }
            }
        }
        return false

    }

    fun saveProduct(product: ProductsResponseItem) = viewModelScope.launch {
        productsRepository.upsert(product)
    }

    fun getProductsFromCart() = productsRepository.getProductsFromCart()


}