package com.sembozdemir.altayersemih.ui.detail.addtobag

import android.support.v7.widget.RecyclerView
import android.view.View
import com.sembozdemir.altayersemih.network.model.OptionsItem

abstract class ConfigOptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: OptionsItem, selected: Boolean)

}
