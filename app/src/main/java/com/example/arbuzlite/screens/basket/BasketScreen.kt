package com.example.arbuzlite.screens.basket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.arbuzlite.R
import com.example.arbuzlite.data.model.Product
import com.example.arbuzlite.screens.home.LoadImage
import com.example.arbuzlite.viewmodel.ProductViewModel

@Composable
fun BasketScreen(viewModel: ProductViewModel) {
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Green, RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Text(
                text = "Total Price: ${basketItems.sumOf { it.price * it.quantity }}",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.align(Alignment.Center),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun BasketItem(product: Product, viewModel: ProductViewModel) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            val context = LocalContext.current
            LoadImage(
                context = context,
                imageUrl = product.imageUrl,
                modifier = Modifier
                    .weight(2f)
                    .height(140.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Column(
                modifier = Modifier
                    .weight(3f)
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = product.name,
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontSize = 16.sp,
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "${product.price}",
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(
                        onClick = {
                            if (product.quantity > 1)
                                viewModel.update(product.copy(quantity = product.quantity - 1))
                        },
                        enabled = product.quantity > 1,
                    ){
                        Icon(painterResource(R.drawable.ic_remove), contentDescription = null)
                    }

                    Text(
                        text = "${product.quantity}",
                        fontWeight = FontWeight.Bold
                    )

                    IconButton(
                        onClick = {
                            viewModel.update(product.copy(quantity = product.quantity + 1))
                        }
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = null)
                    }
                }
            }
        }

        Button(
            modifier = Modifier
                .padding(start = 16.dp, bottom = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            onClick = { viewModel.delete(product) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text("Remove")
        }
    }
}