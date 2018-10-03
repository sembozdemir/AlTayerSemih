package com.sembozdemir.altayersemih.core.injection

import android.app.Application
import android.content.Context
import com.sembozdemir.altayersemih.util.ColorMapper
import com.sembozdemir.altayersemih.util.ColorMapperImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideColorMapper(): ColorMapper = ColorMapperImpl()
}