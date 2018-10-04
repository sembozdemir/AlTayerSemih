package com.sembozdemir.altayersemih.ui.detail.photo

import android.os.Bundle
import android.view.View
import com.sembozdemir.altayersemih.R
import com.sembozdemir.altayersemih.core.BaseSimpleFragment
import com.sembozdemir.altayersemih.extensions.setImageUrl
import com.sembozdemir.altayersemih.util.ImageUrl
import kotlinx.android.synthetic.main.fragment_photo.*

class PhotoFragment : BaseSimpleFragment() {

    companion object {

        private const val KEY_IMAGE_URL = "imageUrl"

        fun newInstance(imageUrl: String?) = PhotoFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_IMAGE_URL, imageUrl)
            }
        }
    }

    private val imageUrl by lazy { arguments?.getString(KEY_IMAGE_URL) }

    override fun getLayoutResId() = R.layout.fragment_photo

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageUrl?.let {
            photoView.setImageUrl(ImageUrl.forDetail(it)) {
                fit()
                centerInside()
            }
        }


    }
}