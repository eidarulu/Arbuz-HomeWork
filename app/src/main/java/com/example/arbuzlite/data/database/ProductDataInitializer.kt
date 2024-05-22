package com.example.arbuzlite.data.database

import com.example.arbuzlite.data.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object ProductDataInitializer {
    fun initialize(productDao: ProductDao) {
        CoroutineScope(Dispatchers.IO).launch {
            productDao.insertProduct(Product(name = "Product 1", price = 10.0, imageUrl = "https://via.placeholder.com/150"))
            productDao.insertProduct(Product(name = "Product 2", price = 15.0, imageUrl = "https://via.placeholder.com/150"))
            productDao.insertProduct(Product(name = "Product 3", price = 20.0, imageUrl = "https://via.placeholder.com/150"))
            productDao.insertProduct(Product(name = "Product 4", price = 25.0, imageUrl = "https://via.placeholder.com/150"))
            productDao.insertProduct(Product(name = "Product 5", price = 30.0, imageUrl = "https://via.placeholder.com/150"))
            productDao.insertProduct(Product(name = "Product 1", price = 10.0, imageUrl = "https://via.placeholder.com/150"))
            productDao.insertProduct(Product(name = "Product 2", price = 15.0, imageUrl = "https://via.placeholder.com/150"))
            productDao.insertProduct(Product(name = "Product 3", price = 20.0, imageUrl = "https://via.placeholder.com/150"))
            productDao.insertProduct(Product(name = "Product 4", price = 25.0, imageUrl = "https://via.placeholder.com/150"))
            productDao.insertProduct(Product(name = "Product 5", price = 30.0, imageUrl = "https://via.placeholder.com/150"))
            productDao.insertProduct(Product(name = "Product 1", price = 10.0, imageUrl = "https://via.placeholder.com/150"))
            productDao.insertProduct(Product(name = "Product 2", price = 15.0, imageUrl = "https://via.placeholder.com/150"))
        }
    }
}