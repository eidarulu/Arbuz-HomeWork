package com.example.arbuzlite.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.arbuzlite.data.database.ProductDao
import com.example.arbuzlite.data.model.Product

@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}