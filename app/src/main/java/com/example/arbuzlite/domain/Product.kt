package com.example.arbuzlite.domain

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val imageUrl: String,
    var quantity: Int = 0
)