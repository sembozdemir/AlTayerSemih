package com.sembozdemir.altayersemih.ui.list

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sembozdemir.altayersemih.R
import com.sembozdemir.altayersemih.extensions.autoNotify
import com.sembozdemir.altayersemih.extensions.inflate
import com.sembozdemir.altayersemih.network.model.Hit
import com.sembozdemir.altayersemih.util.ColorMapper

class RecyclerAdapter(
        val items: MutableList<Hit> = mutableListOf(),
        val colorMapper: ColorMapper
): RecyclerView.Adapter<ItemViewHolder>() {

    private var onItemClickFunc: (hit: Hit) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent.inflate(R.layout.item_grid_product)).apply {
            itemView.setOnClickListener {
                onItemClickFunc(items[adapterPosition])
            }
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
        viewHolder.bind(items[position], colorMapper)
    }

    fun onItemClick(func: (hit: Hit) -> Unit) {
        onItemClickFunc = func
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