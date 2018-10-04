package com.sembozdemir.altayersemih.ui.list

import com.sembozdemir.altayersemih.core.BasePresenter
import com.sembozdemir.altayersemih.network.list.ListRepository
import com.sembozdemir.altayersemih.network.model.ListResponse
import com.sembozdemir.altayersemih.util.ColorMapper
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class ListPresenter(
        private val listRepository: ListRepository,
        private val colorMapper: ColorMapper
) : BasePresenter<ListView>() {

    fun fetchList(page: Int = 0) {
        listRepository.fetchList(page)
                .subscribeBy(
                        onSuccess = { listResponse ->
                            handleListResponse(listResponse)
                            ifViewAttached { it.addMoreItems(listResponse.hits) }
                        },
                        onError = { throwable ->
                            Timber.e(throwable)
                            ifViewAttached { it.showError() }
                        }
                )
    }

    fun refreshList() {
        listRepository.fetchList()
                .subscribeBy(
                        onSuccess = { listResponse ->
                            handleListResponse(listResponse)
                            ifViewAttached { it.populateList(listResponse.hits) }
                        },
                        onError = { throwable ->
                            Timber.e(throwable)
                            ifViewAttached { it.showError() }
                        }
                )
    }

    private fun handleListResponse(listResponse: ListResponse) {
        colorMapper.setColorValues(
                listResponse.facets?.color?.colorValue.orEmpty()
        )

        ifViewAttached {
            it.setTotalPages(listResponse.pagination?.totalPages ?: 0)
            it.showCategoryName(listResponse.categoryName.orEmpty())
        }

    }
}