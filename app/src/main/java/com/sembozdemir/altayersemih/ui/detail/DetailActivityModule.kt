package com.sembozdemir.altayersemih.ui.detail

import com.sembozdemir.altayersemih.network.ApiService
import com.sembozdemir.altayersemih.network.product.ProductRepository
import com.sembozdemir.altayersemih.network.product.ProductRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class DetailActivityModule {

    @Provides
    fun provideProductRepository(apiService: ApiService): ProductRepository {
        return ProductRepositoryImpl(apiService)
    }

    @Provides
    fun provideDetailPresenter(productRepository: ProductRepository) = DetailPresenter(productRepository)
}