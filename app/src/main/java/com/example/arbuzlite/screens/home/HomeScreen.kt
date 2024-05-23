package com.example.arbuzlite.screens.home

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.arbuzlite.R
import com.example.arbuzlite.data.model.Product
import com.example.arbuzlite.viewmodel.ProductViewModel

@Composable
fun HomeScreen(viewModel: ProductViewModel) {
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

@Composable
fun ProductItem(product: Product, viewModel: ProductViewModel) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            val context = LocalContext.current
            LoadImage(
                context = context,
                imageUrl = product.imageUrl,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.name,
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = 16.sp,
                )
            )

            Text(
                text = "${product.price}",
                fontWeight = Bold,

            )

            if (product.quantity > 0) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { viewModel.update(product.copy(quantity = 0)) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Remove")
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
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
                        text= "${product.quantity}",
                        fontWeight = Bold,
                    )
                    IconButton(
                        onClick = {
                            viewModel.update(product.copy(quantity = product.quantity + 1))
                        }
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = null)
                    }
                }
            } else {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { viewModel.update(product.copy(quantity = 1)) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
                ) {
                    Text("Add")
                }
            }
        }
    }
}

@Composable
fun LoadImage(context: Context, imageUrl: String, modifier: Modifier = Modifier) {

    AndroidView(
        factory = {
            ImageView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                scaleType = ImageView.ScaleType.CENTER_CROP
            }
        },
        update = { imageView ->
            Glide.with(context)
                .load(imageUrl)
                .apply(RequestOptions().centerCrop())
                .into(imageView)
        },
        modifier = modifier
    )
}