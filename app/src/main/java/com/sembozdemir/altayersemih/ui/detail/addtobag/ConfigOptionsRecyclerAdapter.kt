package com.sembozdemir.altayersemih.ui.detail.addtobag

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sembozdemir.altayersemih.R
import com.sembozdemir.altayersemih.extensions.inflate
import java.lang.IllegalStateException

class ConfigOptionsRecyclerAdapter(
        private val productConfigItem: ProductConfigItem
) : RecyclerView.Adapter<ConfigOptionViewHolder>() {

    private var selectedPos = INVALID_POSITION

    companion object {
        const val INVALID_POSITION = -1
        const val COLOR_VIEW_TYPE = 0
        const val SIZE_VIEW_TYPE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConfigOptionViewHolder {
        return when (viewType) {
            COLOR_VIEW_TYPE -> ColorOptionViewHolder(parent.inflate(R.layout.item_color_option))
            SIZE_VIEW_TYPE -> SizeOptionViewHolder(parent.inflate(R.layout.item_size_option))
            else -> throw IllegalStateException("Option could not found with view type $viewType")
        }.apply {
            itemView.setOnClickListener {
                selectItem(adapterPosition)
            }
        }
    }

    fun selectItem(position: Int) {
        if (position == INVALID_POSITION) return

        notifyItemChanged(selectedPos)
        selectedPos = position
        notifyItemChanged(selectedPos)
    }

    override fun getItemViewType(position: Int): Int {
        return when (productConfigItem.type) {
            ProductConfigItem.COLOR -> COLOR_VIEW_TYPE
            ProductConfigItem.SIZE_CODE -> SIZE_VIEW_TYPE
            else -> throw IllegalStateException(
                    "View type could not found with ${productConfigItem.type}"
            )
        }
    }

    override fun getItemCount() = productConfigItem.optionsItem.size

    override fun onBindViewHolder(viewHolder: ConfigOptionViewHolder, position: Int) {
        viewHolder.bind(productConfigItem.optionsItem[position], selected = selectedPos == position)
    }
}