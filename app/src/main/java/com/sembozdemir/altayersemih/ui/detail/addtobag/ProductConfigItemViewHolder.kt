package com.sembozdemir.altayersemih.ui.detail.addtobag

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.sembozdemir.altayersemih.R
import com.sembozdemir.altayersemih.network.model.OptionsItem
import kotlinx.android.synthetic.main.item_product_config.view.*

class ProductConfigItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var onOptionSelectedFunc: (
            productConfigItem: ProductConfigItem,
            optionsItem: OptionsItem
    ) -> Unit = { _, _ -> }

    fun onOptionSelected(func: (
            productConfigItem: ProductConfigItem,
            optionsItem: OptionsItem
    ) -> Unit) {
        onOptionSelectedFunc = func
    }

    fun bind(item: ProductConfigItem) {
        with(itemView) {
            productConfigTextViewTitle.text = when (item.type) {
                ProductConfigItem.COLOR -> context.getString(R.string.product_config_title_color)
                ProductConfigItem.SIZE_CODE -> context.getString(R.string.product_config_title_size)
                // TODO: add more title of new option type if needed
                else -> ""
            }

            productConfigRecyclerView.layoutManager = LinearLayoutManager(context,
                    LinearLayoutManager.HORIZONTAL, false)

            productConfigRecyclerView.setHasFixedSize(true)

            val configOptionsRecyclerAdapter = ConfigOptionsRecyclerAdapter(item)
            productConfigRecyclerView.adapter = configOptionsRecyclerAdapter

            var selectedPos = item.optionsItem
                    .indexOfFirst { it.label == item.color }

            if (selectedPos == -1) {
                selectedPos = item.optionsItem.indexOfFirst { it.label == item.selectedSizeLabel }
            }

            configOptionsRecyclerAdapter.selectItem(selectedPos)

            configOptionsRecyclerAdapter.onItemClick { optionsItem ->
                onOptionSelectedFunc(item, optionsItem)
            }

        }
    }
}