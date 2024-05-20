package com.example.arbuzlite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.arbuzlite.data.BasketItem
import com.example.arbuzlite.data.Product
import com.example.arbuzlite.data.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    val products = liveData {
        emit(repository.getAllProducts())
    }

    val basketItems = liveData {
        emit(repository.getBasketItems())
    }

    fun insertProduct(product: Product) = viewModelScope.launch {
        repository.insertProduct(product)
    }

    fun insertBasketItem(basketItem: BasketItem) = viewModelScope.launch {
        repository.insertBasketItem(basketItem)
    }
}