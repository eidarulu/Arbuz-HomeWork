package com.example.arbuzlite.data

class ProductRepository private constructor(private val productDao: ProductDao) {

    suspend fun insertProduct(product: Product) = productDao.insertProduct(product)

    suspend fun getAllProducts() = productDao.getAllProducts()

    suspend fun getProductById(productId: Int) = productDao.getProductById(productId)

    companion object {
        // Singleton instantiation
        @Volatile private var instance: ProductRepository? = null

        fun getInstance(productDao: ProductDao) =
            instance ?: synchronized(this) {
                instance ?: ProductRepository(productDao).also { instance = it }
            }
    }
}