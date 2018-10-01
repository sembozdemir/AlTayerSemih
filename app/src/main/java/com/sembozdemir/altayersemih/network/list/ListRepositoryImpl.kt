package com.sembozdemir.altayersemih.network.list

import com.sembozdemir.altayersemih.network.ApiService
import com.sembozdemir.altayersemih.network.model.ListResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListRepositoryImpl(private val apiService: ApiService): ListRepository {

    override fun fetchList(page: Int): Single<ListResponse> {
        return apiService.getList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}