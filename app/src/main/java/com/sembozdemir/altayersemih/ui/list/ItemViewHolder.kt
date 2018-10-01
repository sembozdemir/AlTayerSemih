package com.sembozdemir.altayersemih.ui.list

import android.support.v7.widget.RecyclerView
import android.view.View
import com.sembozdemir.altayersemih.network.model.Hit
import kotlinx.android.synthetic.main.layout_item.view.*

class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(hit: Hit) {
        with(itemView) {
            itemTextViewDesignerName.text = hit.designerCategoryName
            itemTextViewName.text = hit.name
            itemTextViewPrice.text = hit.price.toString()
        }
    }
}