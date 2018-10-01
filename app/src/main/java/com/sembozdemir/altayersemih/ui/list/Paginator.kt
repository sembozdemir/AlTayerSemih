package com.sembozdemir.altayersemih.ui.list

interface Paginator {
    fun setTotalPages(totalPages: Int)
    fun getTotalPages(): Int
    fun getNextPage(): Int
    fun hasNextPage(): Boolean
}