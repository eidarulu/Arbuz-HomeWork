package com.example.arbuzlite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.arbuzlite.data.BasketItem
import com.example.arbuzlite.data.Product
import com.example.arbuzlite.data.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> get() = _products

    private val _basketItems = MutableStateFlow<List<BasketItem>>(emptyList())
    val basketItems: StateFlow<List<BasketItem>> get() = _basketItems

    init {
        fetchProducts()
        fetchBasketItems()
    }

    private fun fetchProducts() = viewModelScope.launch {
        _products.value = repository.getAllProducts()
    }

    private fun fetchBasketItems() = viewModelScope.launch {
        _basketItems.value = repository.getBasketItems()
    }

    fun insertProduct(product: Product) = viewModelScope.launch {
        repository.insertProduct(product)
    }

    fun insertBasketItem(basketItem: BasketItem) = viewModelScope.launch {
        repository.insertBasketItem(basketItem)
    }
}