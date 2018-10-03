package com.sembozdemir.altayersemih.ui.list

import com.sembozdemir.altayersemih.network.ApiService
import com.sembozdemir.altayersemih.network.list.ListRepository
import com.sembozdemir.altayersemih.network.list.ListRepositoryImpl
import com.sembozdemir.altayersemih.util.ColorMapper
import dagger.Module
import dagger.Provides

@Module
class ListActivityModule {

    @Provides
    fun provideListRepository(apiService: ApiService): ListRepository {
        return ListRepositoryImpl(apiService)
    }

    @Provides
    fun providePaginator(): Paginator = PaginatorImpl()

    @Provides
    fun provideListPresenter(
            listRepository: ListRepository, colorMapper: ColorMapper
    ) = ListPresenter(listRepository, colorMapper)
}