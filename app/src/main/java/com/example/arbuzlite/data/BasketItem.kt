package com.example.arbuzlite.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BasketItem(
    @PrimaryKey val productId: Int,
    var quantity: Int
)