package com.example.arbuzlite.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.arbuzlite.data.BasketItem
import com.example.arbuzlite.data.Product
import com.example.arbuzlite.viewmodel.ProductViewModel


@Composable
fun HomeScreen(productViewModel: ProductViewModel) {
    val products = productViewModel.products.collectAsState(initial = emptyList())

    LazyColumn {
        itemsIndexed(products.value) { _, product ->
            ProductCard(
                product = product,
                onAddToBasketClick = { productExtra ->
                    val basketItem = BasketItem(productId = productExtra.id, quantity = 1) // Create a BasketItem from the Product
                    productViewModel.insertBasketItem(basketItem) // Pass the BasketItem to insertBasketItem
                }
            )
        }
    }
}

@Composable
fun ProductCard(product: Product, onAddToBasketClick: (Product) -> Unit) {
    Card(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(model = product.imageUrl),
                contentDescription = null,
                modifier = Modifier.height(180.dp).fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = product.name,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { onAddToBasketClick(product) },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text(text = "Add to Basket")
            }
        }
    }
}