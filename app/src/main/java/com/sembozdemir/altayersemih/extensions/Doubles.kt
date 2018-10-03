package com.sembozdemir.altayersemih.extensions

import java.math.BigDecimal
import java.text.DecimalFormat

const val DECIMAL_FORMAT_PATTERN = "###,###.##"
const val DEFAULT_CURRENCY_SIGN = "$"

fun Double?.asFormattedAmount(
        currencySign: String = DEFAULT_CURRENCY_SIGN,
        decimalFormatPattern: String = DECIMAL_FORMAT_PATTERN
): String {
    val decimalFormat = DecimalFormat(decimalFormatPattern)

    return "$currencySign${decimalFormat.format(BigDecimal(this ?: 0.0))}"
}