package com.sembozdemir.altayersemih.ui.photo

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class PhotoPagerAdapter(
        fragmentManager: FragmentManager,
        private val imageUrls: List<String?>?,
        private val zoomEnabled: Boolean = true
) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int) = PhotoFragment.newInstance(imageUrls?.get(position), zoomEnabled)

    override fun getCount() = imageUrls?.size ?: 1
}