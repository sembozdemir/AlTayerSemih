package com.sembozdemir.altayersemih.ui.list

import dagger.Module
import dagger.Provides

@Module
class ListActivityModule {

    @Provides
    fun provideListPresenter() = ListPresenter()
}