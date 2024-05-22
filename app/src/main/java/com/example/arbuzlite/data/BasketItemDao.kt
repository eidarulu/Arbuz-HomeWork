package com.example.arbuzlite.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BasketItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBasketItem(basketItem: BasketItem)

    @Query("SELECT * FROM BasketItem")
    suspend fun getAllBasketItems(): List<BasketItem>

    @Query("SELECT * FROM BasketItem WHERE productId = :productId")
    suspend fun getBasketItemByProductId(productId: Int): BasketItem?
}