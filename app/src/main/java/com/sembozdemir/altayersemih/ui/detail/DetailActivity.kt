package com.sembozdemir.altayersemih.ui.detail

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.sembozdemir.altayersemih.R
import com.sembozdemir.altayersemih.core.BaseActivity
import com.sembozdemir.altayersemih.extensions.findFragment
import com.sembozdemir.altayersemih.extensions.fragmentTag
import com.sembozdemir.altayersemih.extensions.setHtml
import com.sembozdemir.altayersemih.extensions.setImageUrl
import com.sembozdemir.altayersemih.network.model.Media
import com.sembozdemir.altayersemih.network.model.OptionsItem
import com.sembozdemir.altayersemih.network.model.Product
import com.sembozdemir.altayersemih.ui.detail.addtobag.AddToBagDialogFragment
import com.sembozdemir.altayersemih.ui.detail.addtobag.ProductConfigItem
import com.sembozdemir.altayersemih.ui.photo.PhotoPagerAdapter
import com.sembozdemir.altayersemih.util.ColorMapper
import com.sembozdemir.altayersemih.util.ImageUrl
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

    @Inject
    lateinit var colorMapper: ColorMapper

    private val sku by lazy { intent.getStringExtra(EXTRA_SKU).orEmpty() }

    private var selectedSizeLabel: String? = null

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
        setupColorDrawable(product)

        setupSizeSelection(product, selectedSizeLabel)

        detailLinearLayoutColorAndSize.setOnClickListener {
            handleAddToBagClick(product)
        }

        detailTextViewDescription.setHtml(product.description.orEmpty())

        detailTextViewSizeAndFit.setHtml(product.sizeAndFit.orEmpty())

        setupPhotoViewPager(product.media)

        val fragment = supportFragmentManager.findFragment<AddToBagDialogFragment>()
        fragment?.let {
            it.setProduct(product, selectedSizeLabel)
        }

    }

    private fun setupColorDrawable(product: Product) {

        if (colorMapper.hasHex(product.color.orEmpty())) {
            val colorHex = colorMapper.getHex(product.color.orEmpty()) ?: "#FFFFFF"
            detailImageViewColorCircle.setImageDrawable(ColorDrawable(Color.parseColor(colorHex)))
        } else if (colorMapper.hasImage(product.color.orEmpty())) {
            val image = colorMapper.getImage(product.color.orEmpty()).orEmpty()
            // FIXME: multicolour images could not be loaded perfectly, because base image url does not work here
            detailImageViewColorCircle.setImageUrl(ImageUrl.forDetail(image)) {
                noFade()
            }
        } else {
            val optionsItemColor = product.configurableAttributes
                    .find { it.code == ProductConfigItem.COLOR }
            val colorHex = optionsItemColor?.options
                    ?.find { it.label == product.color }
                    ?.attributeSpecificProperties
                    ?.hex
                    ?: "#FFFFFF"
            detailImageViewColorCircle.setImageDrawable(ColorDrawable(Color.parseColor(colorHex)))
        }
    }

    private fun setupSizeSelection(product: Product, selectedSizeLabel: String?) {
        val optionsItemForSize = product.configurableAttributes
                .find { it.code == ProductConfigItem.SIZE_CODE }
        val sizeLabelStringBuilder = StringBuilder()
        optionsItemForSize?.options?.forEach {
            sizeLabelStringBuilder.append(it.label).append("   ")
        }

        detailTextViewSize.text = SizeOptionSpannableCreator().getSizeOptionText(selectedSizeLabel,
                sizeLabelStringBuilder.toString())
    }

    private fun handleAddToBagClick(product: Product) {
        val addToBagDialogFragment = AddToBagDialogFragment.newInstance(product, selectedSizeLabel)
        addToBagDialogFragment.show(supportFragmentManager, addToBagDialogFragment.fragmentTag)
    }

    private fun setupPhotoViewPager(media: List<Media>?) {
        val imageUrls = media?.mapNotNull { it.src }
        detailViewPagerPhotos.adapter = PhotoPagerAdapter(supportFragmentManager,
                imageUrls, zoomEnabled = false)

        detailCircleIndicatorPhotos.setViewPager(detailViewPagerPhotos)
    }

    override fun onOptionItemSelected(productConfigItem: ProductConfigItem, optionsItem: OptionsItem) {

        when (productConfigItem.type) {
            ProductConfigItem.COLOR -> {
                selectedSizeLabel = null
                val sku = optionsItem.simpleProductSkus.first()
                presenter.loadProduct(sku)
            }
            ProductConfigItem.SIZE_CODE -> {
                selectedSizeLabel = optionsItem.label
                val sku = productConfigItem.sameColorSiblings
                        .intersect(optionsItem.simpleProductSkus)
                        .first()
                presenter.loadProduct(sku)
            }
        }
    }
}