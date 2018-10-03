package com.sembozdemir.altayersemih.ui.list

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.sembozdemir.altayersemih.R
import com.sembozdemir.altayersemih.core.BaseActivity
import com.sembozdemir.altayersemih.extensions.setOnEndlessScrollListener
import com.sembozdemir.altayersemih.network.model.Hit
import com.sembozdemir.altayersemih.ui.detail.DetailActivity
import com.sembozdemir.altayersemih.util.ColorMapper
import kotlinx.android.synthetic.main.activity_list.*
import javax.inject.Inject

class ListActivity : BaseActivity<ListView, ListPresenter>(), ListView {

    companion object {
        private const val SPAN_COUNT = 2
    }

    @Inject
    lateinit var listPresenter: ListPresenter

    @Inject
    lateinit var paginator: Paginator

    @Inject
    lateinit var colorMapper: ColorMapper

    private var recyclerAdapter: RecyclerAdapter? = null

    override fun createPresenter() = listPresenter

    override fun getLayoutResId() = R.layout.activity_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recyclerAdapter = RecyclerAdapter(colorMapper = colorMapper)

        listSwipeRefreshLayout.setOnRefreshListener {
            presenter.refreshList()
        }

        recyclerAdapter?.onItemClick {
            startActivity(DetailActivity.newIntent(this@ListActivity, it.sku.orEmpty()))
        }

        with(listRecyclerView) {
            val gridLayoutManager = GridLayoutManager(this@ListActivity, SPAN_COUNT)
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
            adapter = recyclerAdapter
            setOnEndlessScrollListener(gridLayoutManager) {
                if (paginator.hasNextPage()) {
                    showLoading()
                    presenter.fetchList(paginator.getNextPage())
                }
            }
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
        recyclerAdapter?.updateItems(hits.orEmpty())
    }

    override fun addMoreItems(hits: List<Hit>?) {
        recyclerAdapter?.addItems(hits.orEmpty())
    }

    override fun setTotalPages(totalPages: Int) {
        paginator.setTotalPages(totalPages)
    }
}