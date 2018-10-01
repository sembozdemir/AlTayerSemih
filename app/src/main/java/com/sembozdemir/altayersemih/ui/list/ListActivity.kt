package com.sembozdemir.altayersemih.ui.list

import android.os.Bundle
import android.widget.Toast
import com.sembozdemir.altayersemih.R
import com.sembozdemir.altayersemih.core.BaseActivity
import javax.inject.Inject

class ListActivity : BaseActivity<ListView, ListPresenter>(), ListView {

    @Inject
    lateinit var listPresenter: ListPresenter

    override fun createPresenter() = listPresenter

    override fun getLayoutResId() = R.layout.activity_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Toast.makeText(this, "Hello world!", Toast.LENGTH_LONG).show()
    }
}