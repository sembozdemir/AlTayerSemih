package com.sembozdemir.altayersemih.ui.detail.addtobag

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sembozdemir.altayersemih.R
import com.sembozdemir.altayersemih.extensions.inflate
import com.sembozdemir.altayersemih.network.model.OptionsItem

class ProductConfigsRecyclerAdapter(
        private val items: List<ProductConfigItem>
) : RecyclerView.Adapter<ProductConfigItemViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductConfigItemViewHolder {
        return ProductConfigItemViewHolder(parent.inflate(R.layout.item_product_config)).apply {
            onOptionSelected { productConfigItem, optionsItem ->
                onOptionSelectedFunc(productConfigItem, optionsItem)
            }
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(viewHolder: ProductConfigItemViewHolder, position: Int) {
        viewHolder.bind(items[position])
    }
}