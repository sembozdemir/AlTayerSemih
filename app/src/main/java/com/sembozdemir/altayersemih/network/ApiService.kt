package com.sembozdemir.altayersemih.network

import com.sembozdemir.altayersemih.network.model.ListResponse
import com.sembozdemir.altayersemih.network.model.Product
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api/women/clothing")
    fun getList(@Query("p") page: Int): Single<ListResponse>

    @GET("/product/findbyslug")
    fun getProduct(@Query("slug") slug: String): Single<Product>

}