package com.sembozdemir.altayersemih.ui.detail.addtobag

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sembozdemir.altayersemih.R
import com.sembozdemir.altayersemih.extensions.inflate

class ProductConfigsRecyclerAdapter(
        private val items: List<ProductConfigItem>
) : RecyclerView.Adapter<ProductConfigItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductConfigItemViewHolder {
        return ProductConfigItemViewHolder(parent.inflate(R.layout.item_product_config))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(viewHolder: ProductConfigItemViewHolder, position: Int) {
        viewHolder.bind(items[position])
    }
}