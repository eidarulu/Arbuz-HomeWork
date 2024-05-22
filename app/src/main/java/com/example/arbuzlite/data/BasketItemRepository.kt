package com.example.arbuzlite.data

class BasketItemRepository private constructor(private val basketItemDao: BasketItemDao) {

    suspend fun insertBasketItem(basketItem: BasketItem) = basketItemDao.insertBasketItem(basketItem)

    suspend fun getAllBasketItems() = basketItemDao.getAllBasketItems()

    suspend fun getBasketItemByProductId(productId: Int) = basketItemDao.getBasketItemByProductId(productId)

    companion object {
        // Singleton instantiation
        @Volatile private var instance: BasketItemRepository? = null

        fun getInstance(basketItemDao: BasketItemDao) =
            instance ?: synchronized(this) {
                instance ?: BasketItemRepository(basketItemDao).also { instance = it }
            }
    }
}