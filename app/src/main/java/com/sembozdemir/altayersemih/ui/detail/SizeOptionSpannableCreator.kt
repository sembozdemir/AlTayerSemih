package com.sembozdemir.altayersemih.ui.detail

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan

class SizeOptionSpannableCreator {

    fun getSizeOptionText(selectedSizeLabel: String?, sizeLabels: String): CharSequence {
        if (selectedSizeLabel != null) {
            val startIndex = sizeLabels.indexOf(selectedSizeLabel)

            if (startIndex != -1) {
                val endIndex = startIndex + selectedSizeLabel.length

                return SpannableStringBuilder(sizeLabels).apply {
                    setSpan(StyleSpan(Typeface.BOLD), startIndex, endIndex,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    setSpan(RelativeSizeSpan(1.2f), startIndex, endIndex,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
            }
        }
        return sizeLabels
    }
}