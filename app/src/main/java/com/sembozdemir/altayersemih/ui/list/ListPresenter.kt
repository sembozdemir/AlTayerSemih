package com.sembozdemir.altayersemih.ui.list

import com.sembozdemir.altayersemih.core.BasePresenter
import com.sembozdemir.altayersemih.network.list.ListRepository
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class ListPresenter(private val listRepository: ListRepository) : BasePresenter<ListView>() {

    fun fetchList() {
        listRepository.fetchList()
                .subscribeBy(
                        onSuccess = { listResponse ->
                            Timber.d(listResponse.categoryName)
                        },
                        onError = { Timber.e(it) }
                )
    }
}