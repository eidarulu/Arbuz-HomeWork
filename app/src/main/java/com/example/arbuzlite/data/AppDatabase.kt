package com.example.arbuzlite.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Product::class, BasketItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun basketItemDao(): BasketItemDao
}