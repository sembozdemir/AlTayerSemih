package com.sembozdemir.altayersemih.network.product

import com.sembozdemir.altayersemih.network.ApiService
import com.sembozdemir.altayersemih.network.model.Product
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductRepositoryImpl(private val apiService: ApiService): ProductRepository {

    override fun getProduct(slug: String): Single<Product> {
        return apiService.getProduct(slug)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}