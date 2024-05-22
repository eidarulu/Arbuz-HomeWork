package com.example.arbuzlite.screens.basket

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.arbuzlite.data.model.Product
import com.example.arbuzlite.viewmodel.ProductViewModel

@Composable
fun BasketScreen(navController: NavController, viewModel: ProductViewModel) {
    val products by viewModel.allProducts.collectAsState(initial = emptyList())
    val basketItems = products.filter { it.quantity > 0 }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(basketItems.size) { index ->
                BasketItem(product = basketItems[index], viewModel)
            }
        }
        Text(
            text = "Total Price: $${basketItems.sumOf { it.price * it.quantity }}",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Composable
fun BasketItem(product: Product, viewModel: ProductViewModel) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(150.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = product.name)
            Text(text = "$${product.price}")
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        if (product.quantity > 1)
                            viewModel.insert(product.copy(quantity = product.quantity - 1))
                    },
                    enabled = product.quantity > 1
                ) {
                    Text("-")
                }
                Text("${product.quantity}")
                Button(
                    onClick = { viewModel.insert(product.copy(quantity = product.quantity + 1)) }
                ) {
                    Text("+")
                }
            }
            Button(
                onClick = { viewModel.delete(product) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Remove")
            }
        }
    }
}