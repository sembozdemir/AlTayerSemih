package com.sembozdemir.altayersemih.ui.detail.addtobag

import android.support.v7.widget.RecyclerView
import android.view.View
import com.sembozdemir.altayersemih.network.model.OptionsItem

/**
 * Base class for options view holder.
 *
 * To add a new option, extend this ViewHolder class and configure it for your needs.
 */
abstract class ConfigOptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: OptionsItem, selected: Boolean)

}
