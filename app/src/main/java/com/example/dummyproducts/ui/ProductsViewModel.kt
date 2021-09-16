package com.example.dummyproducts.ui

import androidx.lifecycle.ViewModel
import com.example.dummyproducts.repositories.ProductsRepository
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
private val productsRepository: ProductsRepository
): ViewModel() {

}