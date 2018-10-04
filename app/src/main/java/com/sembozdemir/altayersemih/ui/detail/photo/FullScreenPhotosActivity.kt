package com.sembozdemir.altayersemih.ui.detail.photo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.sembozdemir.altayersemih.R
import com.sembozdemir.altayersemih.core.BaseSimpleActivity
import kotlinx.android.synthetic.main.activity_full_screen_photos.*

class FullScreenPhotosActivity : BaseSimpleActivity() {

    companion object {
        const val EXTRA_IMAGE_URLS = "images"
        const val EXTRA_CURRENT_ITEM = "currentItem"

        fun newIntent(context: Context, imageUrls: List<String>, currentItem: Int): Intent {
            return Intent(context, FullScreenPhotosActivity::class.java)
                    .putStringArrayListExtra(EXTRA_IMAGE_URLS, ArrayList(imageUrls))
                    .putExtra(EXTRA_CURRENT_ITEM, currentItem)
        }
    }

    private val imageUrls by lazy { intent.getStringArrayListExtra(EXTRA_IMAGE_URLS) }

    private val currentItem by lazy { intent.getIntExtra(EXTRA_CURRENT_ITEM, 0) }

    override fun getLayoutResId() = R.layout.activity_full_screen_photos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fullScreenPhotosViewPager.adapter = PhotoPagerAdapter(supportFragmentManager, imageUrls)
        fullScreenCircleIndicator.setViewPager(fullScreenPhotosViewPager)

        fullScreenPhotosViewPager.currentItem = currentItem
    }

    override fun onBackPressed() {

        setResult(Activity.RESULT_OK,
                Intent().putExtra(EXTRA_CURRENT_ITEM, fullScreenPhotosViewPager.currentItem))

        super.onBackPressed()
    }
}