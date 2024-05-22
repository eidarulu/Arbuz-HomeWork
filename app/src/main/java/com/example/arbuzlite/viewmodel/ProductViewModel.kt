package com.example.arbuzlite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arbuzlite.data.Product
import com.example.arbuzlite.data.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    fun insertProduct(product: Product) = viewModelScope.launch {
        repository.insertProduct(product)
    }

    suspend fun getAllProducts() = repository.getAllProducts()

    suspend fun getProductById(productId: Int) = repository.getProductById(productId)
}