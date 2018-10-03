package com.sembozdemir.altayersemih.ui.list

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.view.View
import com.sembozdemir.altayersemih.extensions.setImageUrl
import com.sembozdemir.altayersemih.network.model.Hit
import com.sembozdemir.altayersemih.util.ColorMapper
import com.sembozdemir.altayersemih.util.ImageUrl
import kotlinx.android.synthetic.main.item_grid_product.view.*

class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(hit: Hit, colorMapper: ColorMapper) {
        with(itemView) {
            val colorHex = colorMapper.getHex(hit.color.orEmpty()) ?: "#000000"
            itemImageView.setImageUrl(ImageUrl.forList(hit.image.orEmpty())) {
                fit().centerCrop().placeholder(ColorDrawable(Color.parseColor(colorHex)))
            }
            itemTextViewDesignerName.text = hit.designerCategoryName
            itemTextViewName.text = hit.name
            itemTextViewPrice.text = hit.price.toString()
        }
    }
}