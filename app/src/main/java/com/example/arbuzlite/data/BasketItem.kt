package com.example.arbuzlite.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BasketItem(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val productId: Int,
    var quantity: Int
)