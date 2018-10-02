package com.sembozdemir.altayersemih.ui.detail.addtobag

import android.view.View
import com.sembozdemir.altayersemih.R
import com.sembozdemir.altayersemih.extensions.setImageUrl
import com.sembozdemir.altayersemih.extensions.setVisible
import com.sembozdemir.altayersemih.network.model.OptionsItem
import com.sembozdemir.altayersemih.util.ImageUrl
import kotlinx.android.synthetic.main.item_color_option.view.*

class ColorOptionViewHolder(itemView: View) : ConfigOptionViewHolder(itemView) {

    override fun bind(item: OptionsItem, selected: Boolean) {
        with(itemView) {
            itemColorOptionImageView.setImageUrl(ImageUrl.forDetail(
                    item.attributeSpecificProperties?.swatchImage ?: "")) {
                fit().centerCrop().placeholder(R.drawable.ic_placeholder)
            }

            itemColorOptionViewSelection.setVisible(selected)

            itemColorOptionTextViewLabel.text = item.label
        }
    }
}