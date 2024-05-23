package com.example.arbuzlite.data.database

import com.example.arbuzlite.data.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object ProductDataInitializer {
    fun initialize(productDao: ProductDao) {
        CoroutineScope(Dispatchers.IO).launch {
            productDao.insertProduct(Product(name = "Toothbrush", price = 3535.65, imageUrl = "https://www.dhresource.com/0x0/f2/albu/g10/M00/0C/61/rBVaWV6ylY6AZwAZAAWBQbX9Hyo968.jpg"))
            productDao.insertProduct(Product(name = "Tote Bag", price = 5535.50, imageUrl = "https://i.pinimg.com/originals/57/16/89/571689f6d8daba318d7815bf81c07f56.jpg"))
            productDao.insertProduct(Product(name = "Garden Lights", price = 8826.45, imageUrl = "https://avatars.mds.yandex.net/i?id=6e30d1a80cf8b203adec658d7f32aa2e8c733af7-12244410-images-thumbs&n=13"))
            productDao.insertProduct(Product(name = "Water Bottle", price = 6626.5, imageUrl = "https://avatars.mds.yandex.net/i?id=402b54e07581e09cadd8f72f022d743b-5883552-images-thumbs&n=13"))
            productDao.insertProduct(Product(name = "Coffee Mug", price = 7072.00, imageUrl = "https://avatars.mds.yandex.net/i?id=198974698e9e453a98739c42ae4c39a2b7ed4e30-10812270-images-thumbs&n=13"))
            productDao.insertProduct(Product(name = "Candles", price = 4418.35, imageUrl = "https://avatars.mds.yandex.net/i?id=7d4de5b20e4bbf9a373dac4cd476d833-4787453-images-thumbs&n=13"))
            productDao.insertProduct(Product(name = "Chocolate Bar", price = 1991.70, imageUrl = "https://avatars.mds.yandex.net/i?id=a04992d93682f1e1fd6b03cdf272274f4c97c4ac-8492261-images-thumbs&n=13"))
            productDao.insertProduct(Product(name = "Desk Organizer", price = 13286.15, imageUrl = "https://avatars.mds.yandex.net/i?id=1c48282078864007771fd12ee4248428812dc1db-10766029-images-thumbs&n=13"))
            productDao.insertProduct(Product(name = "Kitchen Towels", price = 3981.81, imageUrl = "https://avatars.mds.yandex.net/i?id=c00ae6ebc47aade58a98850bb0a8ed6ae4b74b9f-12602658-images-thumbs&n=13"))
            productDao.insertProduct(Product(name = "Leather Wallet", price = 17741.65, imageUrl = "https://avatars.mds.yandex.net/i?id=bbbc07859b8ec20696161ec87d296df18d329ed6-9243216-images-thumbs&n=13"))
            productDao.insertProduct(Product(name = "Food Wraps", price = 5314.91, imageUrl = "https://avatars.mds.yandex.net/i?id=38f41b729baa33031c10ea1db8e2fe402374dfa8-8273295-images-thumbs&n=13"))
            productDao.insertProduct(Product(name = "Phone Case", price = 11046.60, imageUrl = "https://avatars.mds.yandex.net/i?id=6c778fce9e369e27fb66bc92a7fd4be7-4577918-images-thumbs&n=13"))
        }
    }
}