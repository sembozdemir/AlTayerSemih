package com.sembozdemir.altayersemih.extensions

import android.support.v7.util.DiffUtil
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.sembozdemir.altayersemih.ui.list.EndlessRecyclerViewScrollListener

fun <T> RecyclerView.Adapter<*>.autoNotify(oldList: List<T>, newList: List<T>, compare: (T, T) -> Boolean) {
    val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return compare(oldList[oldItemPosition], newList[newItemPosition])
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size
    })

    diff.dispatchUpdatesTo(this)
}

fun RecyclerView.setOnEndlessScrollListener(gridLayoutManager: GridLayoutManager, onEndReached: () -> Unit) {
    addOnScrollListener(object : EndlessRecyclerViewScrollListener(gridLayoutManager) {

        override fun onEndReached(page: Int, totalItemsCount: Int, view: RecyclerView) {
            onEndReached()
        }

    })
}