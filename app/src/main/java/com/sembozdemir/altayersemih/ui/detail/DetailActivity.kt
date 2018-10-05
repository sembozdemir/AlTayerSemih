package com.sembozdemir.altayersemih.ui.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityOptionsCompat
import android.view.View
import com.sembozdemir.altayersemih.R
import com.sembozdemir.altayersemih.core.BaseActivity
import com.sembozdemir.altayersemih.extensions.*
import com.sembozdemir.altayersemih.network.model.Media
import com.sembozdemir.altayersemih.network.model.OptionsItem
import com.sembozdemir.altayersemih.network.model.Product
import com.sembozdemir.altayersemih.ui.detail.addtobag.AddToBagDialogFragment
import com.sembozdemir.altayersemih.ui.detail.addtobag.ProductConfigItem
import com.sembozdemir.altayersemih.ui.detail.photo.FullScreenPhotosActivity
import com.sembozdemir.altayersemih.ui.detail.photo.PhotoPagerAdapter
import com.sembozdemir.altayersemih.util.ColorMapper
import com.sembozdemir.altayersemih.util.ImageUrl
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : BaseActivity<DetailView, DetailPresenter>(), DetailView,
        AddToBagDialogFragment.OnOptionItemSelectedListener {

    companion object {

        private const val EXTRA_SKU = "sku"
        private const val EXTRA_IMAGE_URL = "imageUrl"
        private const val REQUEST_CODE_CURRENT_ITEM = 10

        fun newIntent(context: Context, sku: String, imageUrl: String): Intent {
            return Intent(context, DetailActivity::class.java)
                    .putExtra(EXTRA_SKU, sku)
                    .putExtra(EXTRA_IMAGE_URL, imageUrl)
        }

    }

    @Inject
    lateinit var detailPresenter: DetailPresenter

    @Inject
    lateinit var colorMapper: ColorMapper

    private val sku by lazy { intent.getStringExtra(EXTRA_SKU).orEmpty() }

    private val imageUrl by lazy { intent.getStringExtra(EXTRA_IMAGE_URL).orEmpty() }

    private var selectedSizeLabel: String? = null

    override fun createPresenter() = detailPresenter

    override fun getLayoutResId() = R.layout.activity_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableHomeAsUp(detailToolbar)

        showLoading()

        supportPostponeEnterTransition()
        Picasso.get().load(ImageUrl.forDetail(imageUrl))
                .fit()
                .centerCrop()
                .into(detailImageViewPhoto) {
                    onSuccess {
                        scheduleStartPostponedTransition(detailImageViewPhoto)
                        presenter.loadProduct(sku)
                    }

                    onError {
                        scheduleStartPostponedTransition(detailImageViewPhoto)
                        presenter.loadProduct(sku)
                    }
                }

    }

    override fun showProduct(product: Product) {

        hideLoading()

        detailToolbar.title = product.name

        detailTextViewName.text = product.name

        detailTextViewDesigner.text = product.designerCategoryName

        detailTextViewPrice.text = product.price.asFormattedAmount()

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
        fragment?.setProduct(product, selectedSizeLabel)

    }

    override fun showError(retrySku: String) {
        hideLoading()
        detailCoordinatorLayout.snack(R.string.general_error, Snackbar.LENGTH_INDEFINITE) {
            action(R.string.retry) {
                showLoading()
                presenter.loadProduct(retrySku)
            }
        }
    }

    private fun showLoading() {
        detailProgressBar.show()
        detailProgressBar.setVisible(true)
    }

    private fun hideLoading() {
        detailProgressBar.hide()
    }

    private fun scheduleStartPostponedTransition(view: View) {
        view.doOnPreDraw {
            supportStartPostponedEnterTransition()
        }
    }

    private fun setupColorDrawable(product: Product) {

        if (colorMapper.hasHex(product.color.orEmpty())) {
            val colorHex = colorMapper.getHex(product.color.orEmpty()) ?: "#FFFFFF"
            detailImageViewColorCircle.setImageDrawable(ColorDrawable(Color.parseColor(colorHex)))
        } else if (colorMapper.hasImage(product.color.orEmpty())) {
            val image = colorMapper.getImage(product.color.orEmpty()).orEmpty()
            detailImageViewColorCircle.setImageUrl(ImageUrl.forSwatch(image)) {
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
        sizeLabelStringBuilder.append(" · ")
        optionsItemForSize?.options?.forEach {
            sizeLabelStringBuilder.append(it.label).append(" · ")
        }

        detailTextViewSize.text = SizeOptionSpannableCreator().getSizeOptionText(
                "· $selectedSizeLabel ·",
                sizeLabelStringBuilder.toString())
    }

    private fun handleAddToBagClick(product: Product) {
        val addToBagDialogFragment = AddToBagDialogFragment.newInstance(product, selectedSizeLabel)
        addToBagDialogFragment.show(supportFragmentManager, addToBagDialogFragment.fragmentTag)
    }

    private fun setupPhotoViewPager(media: List<Media>?) {
        val imageUrls = media?.mapNotNull { it.src }.orEmpty()
        detailViewPagerPhotos.adapter = PhotoPagerAdapter(supportFragmentManager, imageUrls,
                asFullScreen = false)

        detailCircleIndicatorPhotos.setViewPager(detailViewPagerPhotos)

        detailViewPagerPhotos.setOnClickListener {
            navigateToFullScreenPhoto(imageUrls)
        }
    }

    private fun navigateToFullScreenPhoto(imageUrls: List<String>) {

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@DetailActivity,
                detailViewPagerPhotos, getString(R.string.transition_fullscreen_photo))

        startActivityForResult(FullScreenPhotosActivity.newIntent(this,
                imageUrls, detailViewPagerPhotos.currentItem),
                REQUEST_CODE_CURRENT_ITEM,
                options.toBundle())
    }

    override fun onOptionItemSelected(productConfigItem: ProductConfigItem, optionsItem: OptionsItem) {

        when (productConfigItem.type) {
            ProductConfigItem.COLOR -> {
                selectedSizeLabel = null
                showLoading()
                presenter.loadSelectedColor(optionsItem)
            }
            ProductConfigItem.SIZE_CODE -> {
                selectedSizeLabel = optionsItem.label
                showLoading()
                presenter.loadSelectedSize(productConfigItem.sameColorSiblings, optionsItem)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_CURRENT_ITEM && resultCode == Activity.RESULT_OK) {
            val currentItem = data?.getIntExtra(FullScreenPhotosActivity.EXTRA_CURRENT_ITEM, 0) ?: 0
            detailViewPagerPhotos.currentItem = currentItem
        }
    }

    override fun finishAfterTransition() {
        finish()
    }
}