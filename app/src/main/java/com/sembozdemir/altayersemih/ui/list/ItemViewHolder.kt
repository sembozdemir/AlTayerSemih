package com.sembozdemir.altayersemih.ui.list

import android.support.v7.widget.RecyclerView
import android.view.View
import com.sembozdemir.altayersemih.R
import com.sembozdemir.altayersemih.extensions.setImageUrl
import com.sembozdemir.altayersemih.network.model.Hit
import com.sembozdemir.altayersemih.util.ImageUrl
import kotlinx.android.synthetic.main.layout_item.view.*

class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(hit: Hit) {
        with(itemView) {
            itemImageView.setImageUrl(ImageUrl.forList(hit.image.orEmpty())) {
                fit().centerCrop().placeholder(R.drawable.ic_placeholder)
            }
            itemTextViewDesignerName.text = hit.designerCategoryName
            itemTextViewName.text = hit.name
            itemTextViewPrice.text = hit.price.toString()
        }
    }
}