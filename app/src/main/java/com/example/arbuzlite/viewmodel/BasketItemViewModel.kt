package com.example.arbuzlite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arbuzlite.data.BasketItem
import com.example.arbuzlite.data.BasketItemRepository
import kotlinx.coroutines.launch

class BasketItemViewModel(private val repository: BasketItemRepository) : ViewModel() {

    fun insertBasketItem(basketItem: BasketItem) = viewModelScope.launch {
        repository.insertBasketItem(basketItem)
    }

    suspend fun getAllBasketItems() = repository.getAllBasketItems()

    suspend fun getBasketItemByProductId(productId: Int) = repository.getBasketItemByProductId(productId)
}