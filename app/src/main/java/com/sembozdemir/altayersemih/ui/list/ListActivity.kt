package com.sembozdemir.altayersemih.ui.list

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.widget.Toast
import com.sembozdemir.altayersemih.R
import com.sembozdemir.altayersemih.core.BaseActivity
import com.sembozdemir.altayersemih.network.model.Hit
import kotlinx.android.synthetic.main.activity_list.*
import javax.inject.Inject

class ListActivity : BaseActivity<ListView, ListPresenter>(), ListView {

    companion object {
        private const val SPAN_COUNT = 2
    }

    @Inject
    lateinit var listPresenter: ListPresenter

    private val recyclerAdapter = RecyclerAdapter(mutableListOf())

    override fun createPresenter() = listPresenter

    override fun getLayoutResId() = R.layout.activity_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listSwipeRefreshLayout.setOnRefreshListener {
            presenter.fetchList()
        }

        with(listRecyclerView) {
            layoutManager = GridLayoutManager(this@ListActivity, SPAN_COUNT)
            setHasFixedSize(true)
            adapter = recyclerAdapter
        }

        showLoading()
        presenter.fetchList()
    }

    private fun showLoading() {
        listSwipeRefreshLayout.isRefreshing = true
    }

    private fun hideLoading() {
        listSwipeRefreshLayout.isRefreshing = false
    }

    override fun showCategoryName(categoryName: String) {
        hideLoading()
        listToolbar.title = categoryName
    }

    override fun populateList(hits: List<Hit>?) {
        recyclerAdapter.addItems(hits.orEmpty())
    }
}