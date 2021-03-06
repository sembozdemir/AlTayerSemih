package com.sembozdemir.altayersemih.ui.detail.addtobag

import com.sembozdemir.altayersemih.network.model.OptionsItem


data class ProductConfigItem(
        val type: String,
        val color: String,
        val selectedSizeLabel: String?,
        val sameColorSiblings: List<String>,
        val optionsItem: List<OptionsItem>
) {

    companion object {
        const val COLOR = "color"
        const val SIZE_CODE = "sizeCode"
        // TODO: add more `code` string of new option type if needed
    }

}