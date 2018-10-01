package com.sembozdemir.altayersemih.ui.list

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sembozdemir.altayersemih.R
import com.sembozdemir.altayersemih.extensions.autoNotify
import com.sembozdemir.altayersemih.extensions.inflate
import com.sembozdemir.altayersemih.network.model.Hit

class RecyclerAdapter(
        val items: MutableList<Hit> = mutableListOf()
): RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
            parent.inflate(R.layout.layout_item)
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
        viewHolder.bind(items[position])
    }

    fun updateItems(newItems: List<Hit>) {
        val oldItems = items.toList()
        items.clear()
        items.addAll(newItems)

        autoNotify(oldItems, items) { old, new ->
            old.productId == new.productId
        }
    }

    fun addItems(newItems: List<Hit>) {
        val oldItems = items.toList()
        items.addAll(newItems)

        autoNotify(oldItems, items) { old, new ->
            old.productId == new.productId
        }
    }
}