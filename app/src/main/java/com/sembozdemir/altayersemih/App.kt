package com.sembozdemir.altayersemih

import android.util.Log
import com.sembozdemir.altayersemih.core.injection.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .also { it.inject(this) }

    override fun onCreate() {
        super.onCreate()

        Timber.plant(if (BuildConfig.DEBUG) Timber.DebugTree() else CrashReportingTree())
    }

    private inner class CrashReportingTree : Timber.Tree() {

        override fun log(priority: Int, tag: String?, message: String,
                         throwable: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }

            // TODO: report warnings and errors by using a crash reporting tool
        }
    }

}