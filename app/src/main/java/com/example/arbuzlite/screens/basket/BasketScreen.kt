package com.example.arbuzlite.screens.basket

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun BasketScreen(viewModel: BasketItemViewModel) {
    val basketItems by viewModel.getAllBasketItems().collectAsState(initial = emptyList())

    LazyColumn {
        items(basketItems) { basketItem ->
            Text(text = "Basket Item ID: ${basketItem.id}, Product ID: ${basketItem.productId}, Quantity: ${basketItem.quantity}")
        }
    }
}