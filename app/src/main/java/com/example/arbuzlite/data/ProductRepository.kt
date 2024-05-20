package com.example.arbuzlite.data

class ProductRepository(private val productDao: ProductDao) {
    suspend fun getAllProducts(): List<Product> {
        return productDao.getAllProducts()
    }

    suspend fun insertProduct(product: Product) {
        productDao.insertProduct(product)
    }

    suspend fun getBasketItems(): List<BasketItem> {
        return productDao.getBasketItems()
    }

    suspend fun insertBasketItem(basketItem: BasketItem) {
        productDao.insertBasketItem(basketItem)
    }
}