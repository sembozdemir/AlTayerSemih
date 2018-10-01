package com.sembozdemir.altayersemih.core.injection

import com.sembozdemir.altayersemih.ui.list.ListActivity
import com.sembozdemir.altayersemih.ui.list.ListActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [ListActivityModule::class])
    abstract fun bindListActivity(): ListActivity
}