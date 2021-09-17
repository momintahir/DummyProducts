package com.example.dummyproducts.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dummyproducts.repositories.ProductsRepository

class ProductsViewModelProviderFactory(
    private val productsRepository: ProductsRepository,
    val app: Application

) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductsViewModel(productsRepository, app) as T
    }
}