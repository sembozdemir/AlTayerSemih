package com.sembozdemir.altayersemih.network.product

import com.sembozdemir.altayersemih.network.model.Product
import io.reactivex.Single

interface ProductRepository {

    fun getProduct(slug: String): Single<Product>

}