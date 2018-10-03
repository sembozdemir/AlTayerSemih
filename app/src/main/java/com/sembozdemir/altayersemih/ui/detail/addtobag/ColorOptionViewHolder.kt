package com.sembozdemir.altayersemih.ui.detail.addtobag

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import com.sembozdemir.altayersemih.extensions.setImageUrl
import com.sembozdemir.altayersemih.extensions.setVisible
import com.sembozdemir.altayersemih.network.model.OptionsItem
import com.sembozdemir.altayersemih.util.ImageUrl
import kotlinx.android.synthetic.main.item_color_option.view.*

class ColorOptionViewHolder(itemView: View) : ConfigOptionViewHolder(itemView) {

    override fun bind(item: OptionsItem, selected: Boolean) {
        with(itemView) {
            val colorHex = item.attributeSpecificProperties?.hex ?: "#000000"
            itemColorOptionImageView.setImageUrl(ImageUrl.forDetail(
                    item.attributeSpecificProperties?.swatchImage ?: "")) {
                fit().centerCrop().placeholder(ColorDrawable(Color.parseColor(colorHex)))
            }

            itemColorOptionViewSelection.setVisible(selected)

            itemColorOptionTextViewLabel.text = item.label
        }
    }
}