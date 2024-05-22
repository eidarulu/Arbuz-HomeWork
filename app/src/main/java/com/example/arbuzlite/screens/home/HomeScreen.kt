package com.example.arbuzlite.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.arbuzlite.data.model.Product
import com.example.arbuzlite.viewmodel.ProductViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: ProductViewModel) {
    val products by viewModel.allProducts.collectAsState(initial = emptyList())

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(16.dp)
    ) {
        items(products.size) { index ->
            ProductItem(product = products[index], viewModel)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductItem(product: Product, viewModel: ProductViewModel) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(200.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            GlideImage(
                model = product.imageUrl,
                contentDescription = "Product Image",
                modifier = Modifier.height(100.dp).fillMaxWidth()
            )
            Text(text = product.name)
            Text(text = "${product.price}")
            if (product.quantity > 0) {
                Button(
                    onClick = { viewModel.delete(product) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Remove")
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            if (product.quantity > 1)
                                viewModel.insert(product.copy(quantity = product.quantity - 1))
                        }
                    ) {
                        Text("-")
                    }
                    Text("${product.quantity}")
                    Button(
                        onClick = {
                            viewModel.insert(product.copy(quantity = product.quantity + 1))
                        }
                    ) {
                        Text("+")
                    }
                }
            } else {
                Button(
                    onClick = { viewModel.insert(product.copy(quantity = 1)) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
                ) {
                    Text("Add")
                }
            }
        }
    }
}