package com.sembozdemir.altayersemih

import com.sembozdemir.altayersemih.core.injection.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .also { it.inject(this) }


}