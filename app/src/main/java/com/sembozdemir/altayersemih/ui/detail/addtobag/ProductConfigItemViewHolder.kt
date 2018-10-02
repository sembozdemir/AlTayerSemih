package com.sembozdemir.altayersemih.ui.detail.addtobag

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.sembozdemir.altayersemih.R
import kotlinx.android.synthetic.main.item_product_config.view.*

class ProductConfigItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: ProductConfigItem) {
        with(itemView) {
            productConfigTextViewTitle.text = when (item.type) {
                ProductConfigItem.COLOR -> context.getString(R.string.product_config_title_color)
                ProductConfigItem.SIZE_CODE -> context.getString(R.string.product_config_title_size)
                else -> ""
            }

            productConfigRecyclerView.layoutManager = LinearLayoutManager(context,
                    LinearLayoutManager.HORIZONTAL, false)

            productConfigRecyclerView.setHasFixedSize(true)

            val configOptionsRecyclerAdapter = ConfigOptionsRecyclerAdapter(item)
            productConfigRecyclerView.adapter = configOptionsRecyclerAdapter

            val selectedPos = item.optionsItem.indexOfFirst {
                it.label == item.color
            }

            configOptionsRecyclerAdapter.selectItem(selectedPos)

        }
    }
}