package com.sembozdemir.altayersemih.ui.list

class PaginatorImpl: Paginator {

    private var page = 0

    private var totalPages = 0

    override fun setTotalPages(totalPages: Int) {
        this.totalPages = totalPages
    }

    override fun getTotalPages() = totalPages

    override fun getNextPage(): Int {
        page++

        check(page <= totalPages) { "Page count is reached total pages count." }

        return page
    }

    override fun hasNextPage(): Boolean {
        val nextPage = page + 1
        return nextPage <= totalPages
    }

    override fun getCurrentPage() = page
}