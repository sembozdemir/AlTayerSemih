package com.sembozdemir.altayersemih.network.list

import com.sembozdemir.altayersemih.network.model.ListResponse
import io.reactivex.Single

interface ListRepository {

    fun fetchList(page: Int = 0): Single<ListResponse>

}