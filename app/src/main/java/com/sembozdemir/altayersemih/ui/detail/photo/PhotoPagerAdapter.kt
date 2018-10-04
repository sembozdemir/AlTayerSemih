package com.sembozdemir.altayersemih.ui.detail.photo

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.sembozdemir.altayersemih.ui.detail.ImagePageFragment

class PhotoPagerAdapter(
        fragmentManager: FragmentManager,
        private val imageUrls: List<String?>,
        private val asFullScreen: Boolean = true
) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment =
            if (asFullScreen) PhotoFragment.newInstance(imageUrls[position])
            else ImagePageFragment.newInstance(imageUrls[position])

    override fun getCount() = imageUrls.size
}