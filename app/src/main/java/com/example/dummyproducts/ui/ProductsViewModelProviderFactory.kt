package com.example.dummyproducts.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dummyproducts.repositories.ProductsRepository

class ProductsViewModelProviderFactory(
    private val productsRepository: ProductsRepository

) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductsViewModel(productsRepository) as T
    }
}