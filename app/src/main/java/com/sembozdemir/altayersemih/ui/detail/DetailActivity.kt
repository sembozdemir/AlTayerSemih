package com.sembozdemir.altayersemih.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.sembozdemir.altayersemih.R
import com.sembozdemir.altayersemih.core.BaseActivity
import com.sembozdemir.altayersemih.network.model.Media
import com.sembozdemir.altayersemih.network.model.Product
import com.sembozdemir.altayersemih.ui.photo.PhotoPagerAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : BaseActivity<DetailView, DetailPresenter>(), DetailView {

    companion object {

        private const val EXTRA_SKU = "sku"

        fun newIntent(context: Context, sku: String): Intent = Intent(context, DetailActivity::class.java)
                .putExtra(EXTRA_SKU, sku)

    }

    @Inject
    lateinit var detailPresenter: DetailPresenter

    private val sku by lazy { intent.getStringExtra(EXTRA_SKU).orEmpty() }

    override fun createPresenter() = detailPresenter

    override fun getLayoutResId() = R.layout.activity_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.loadProduct(sku)
    }

    override fun showProduct(product: Product) {

        enableHomeAsUp(detailToolbar)

        detailToolbar.title = product.name

        detailTextViewDescription.text = product.name

        setupPhotoViewPager(product.media)

    }

    private fun setupPhotoViewPager(media: List<Media>?) {
        val imageUrls = media?.mapNotNull { it.src }
        detailViewPagerPhotos.adapter = PhotoPagerAdapter(supportFragmentManager,
                imageUrls, zoomEnabled = false)

        detailCircleIndicatorPhotos.setViewPager(detailViewPagerPhotos)
    }
}