package com.sembozdemir.altayersemih.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.sembozdemir.altayersemih.R
import com.sembozdemir.altayersemih.core.BaseActivity
import com.sembozdemir.altayersemih.extensions.findFragment
import com.sembozdemir.altayersemih.extensions.fragmentTag
import com.sembozdemir.altayersemih.network.model.Media
import com.sembozdemir.altayersemih.network.model.OptionsItem
import com.sembozdemir.altayersemih.network.model.Product
import com.sembozdemir.altayersemih.ui.detail.addtobag.AddToBagDialogFragment
import com.sembozdemir.altayersemih.ui.detail.addtobag.ProductConfigItem
import com.sembozdemir.altayersemih.ui.photo.PhotoPagerAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : BaseActivity<DetailView, DetailPresenter>(), DetailView,
        AddToBagDialogFragment.OnOptionItemSelectedListener {

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

        detailTextViewName.text = product.name

        detailTextViewDesigner.text = product.designerCategoryName

        detailTextViewPrice.text = product.price.toString()

        detailButtonBottomSheetExpand.setOnClickListener {
            handleAddToBagClick(product)
        }

        detailTextViewColor.text = product.color

        detailLinearLayoutColor.setOnClickListener {
            handleAddToBagClick(product)
        }

        detailTextViewSize.text = product.sizeCode

        detailLinearLayoutSize.setOnClickListener {
            handleAddToBagClick(product)
        }

        setupPhotoViewPager(product.media)

        val fragment = supportFragmentManager.findFragment<AddToBagDialogFragment>()
        fragment?.let {
            it.setProduct(product)
        }

    }

    private fun handleAddToBagClick(product: Product) {
        val addToBagDialogFragment = AddToBagDialogFragment.newInstance(product)
        addToBagDialogFragment.show(supportFragmentManager, addToBagDialogFragment.fragmentTag)
    }

    private fun setupPhotoViewPager(media: List<Media>?) {
        val imageUrls = media?.mapNotNull { it.src }
        detailViewPagerPhotos.adapter = PhotoPagerAdapter(supportFragmentManager,
                imageUrls, zoomEnabled = false)

        detailCircleIndicatorPhotos.setViewPager(detailViewPagerPhotos)
    }

    override fun onOptionItemSelected(otherProductConfigItem: ProductConfigItem?, optionsItem: OptionsItem) {

        if (otherProductConfigItem == null) {
            val sku = optionsItem.simpleProductSkus.first()
            presenter.loadProduct(sku)
            return
        }

        when (otherProductConfigItem.type) {
            ProductConfigItem.COLOR -> {
                val position = otherProductConfigItem.optionsItem
                        .indexOfFirst { detailTextViewColor.text == it.label }

                if (position != -1) {
                    val sku = optionsItem.simpleProductSkus[position]
                    presenter.loadProduct(sku)
                }
            }
            ProductConfigItem.SIZE_CODE -> {
                val position = otherProductConfigItem.optionsItem
                        .indexOfFirst { detailTextViewSize.text == it.label }

                if (position != -1) {
                    val sku = optionsItem.simpleProductSkus[position]
                    presenter.loadProduct(sku)
                }
            }
        }
    }
}