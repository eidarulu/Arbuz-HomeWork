package com.example.arbuzlite.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {
    @Query("SELECT * FROM Product")
    suspend fun getAllProducts(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    @Query("SELECT * FROM BasketItem")
    suspend fun getBasketItems(): List<BasketItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBasketItem(basketItem: BasketItem)
}