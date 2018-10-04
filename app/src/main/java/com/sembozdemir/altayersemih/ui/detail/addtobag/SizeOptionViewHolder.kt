package com.sembozdemir.altayersemih.ui.detail.addtobag

import android.support.v4.content.ContextCompat
import android.view.View
import com.sembozdemir.altayersemih.R
import com.sembozdemir.altayersemih.network.model.OptionsItem
import kotlinx.android.synthetic.main.item_size_option.view.*

class SizeOptionViewHolder(itemView: View) : ConfigOptionViewHolder(itemView) {

    override fun bind(item: OptionsItem, selected: Boolean) {
        with(itemView) {
            setBackgroundColor(
                    when {
                        selected -> ContextCompat.getColor(context, R.color.colorPrimary)
                        item.isInStock == true -> ContextCompat.getColor(context, R.color.white)
                        else -> ContextCompat.getColor(context, R.color.light_gray)
                    }
            )

            itemView.isClickable = item.isInStock == true

            itemSizeOptionTextView.setTextColor(
                    if (selected) ContextCompat.getColor(context, R.color.white)
                    else ContextCompat.getColor(context, R.color.black)
            )

            itemSizeOptionTextView.text = item.label
        }
    }
}