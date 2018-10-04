package com.sembozdemir.altayersemih.ui.detail

import android.os.Bundle
import android.view.View
import com.sembozdemir.altayersemih.R
import com.sembozdemir.altayersemih.core.BaseSimpleFragment
import com.sembozdemir.altayersemih.extensions.setImageUrl
import com.sembozdemir.altayersemih.util.ImageUrl
import kotlinx.android.synthetic.main.fragment_image_page.*

class ImagePageFragment : BaseSimpleFragment() {

    companion object {

        private const val KEY_IMAGE_URL = "imageUrl"

        fun newInstance(imageUrl: String?) = ImagePageFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_IMAGE_URL, imageUrl)
            }
        }
    }

    private val imageUrl by lazy { arguments?.getString(KEY_IMAGE_URL) }

    override fun getLayoutResId() = R.layout.fragment_image_page

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageUrl?.let {
            imagePageImageView.setImageUrl(ImageUrl.forDetail(it)) {
                fit()
                centerCrop()
            }
        }


    }
}