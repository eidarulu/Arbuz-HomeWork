package com.example.arbuzlite.data

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }
}