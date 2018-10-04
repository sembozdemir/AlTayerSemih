package com.sembozdemir.altayersemih.ui.detail

import com.sembozdemir.altayersemih.core.BasePresenter
import com.sembozdemir.altayersemih.network.model.OptionsItem
import com.sembozdemir.altayersemih.network.product.ProductRepository
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class DetailPresenter(
        private val productRepository: ProductRepository
) : BasePresenter<DetailView>() {

    fun loadProduct(sku: String) {
        productRepository.getProduct(sku)
                .subscribeBy(
                        onSuccess = { product ->
                            ifViewAttached { it.showProduct(product) }
                        },
                        onError = { Timber.e(it) }
                )
    }

    fun loadSelectedColor(optionsItem: OptionsItem) {
        val sku = optionsItem.simpleProductSkus.first()
        loadProduct(sku)
    }

    fun loadSelectedSize(sameColorSiblings: List<String>, optionsItem: OptionsItem) {
        val sku = sameColorSiblings
                .intersect(optionsItem.simpleProductSkus)
                .first()
        loadProduct(sku)
    }
}