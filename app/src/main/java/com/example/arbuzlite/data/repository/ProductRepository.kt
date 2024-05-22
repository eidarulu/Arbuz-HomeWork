package com.example.arbuzlite.data.repository

import com.example.arbuzlite.data.database.ProductDao
import com.example.arbuzlite.data.model.Product
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val productDao: ProductDao) {
    val allProducts: Flow<List<Product>> = productDao.getAllProducts()

    suspend fun insert(product: Product) {
        productDao.insertProduct(product)
    }

    suspend fun delete(product: Product) {
        productDao.deleteProduct(product)
    }
}