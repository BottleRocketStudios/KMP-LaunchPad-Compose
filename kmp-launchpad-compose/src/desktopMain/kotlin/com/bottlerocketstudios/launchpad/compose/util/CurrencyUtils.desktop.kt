package com.bottlerocketstudios.launchpad.compose.util

import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

/**
 * Kotlin Multiplatform function - convertToCurrencyString
 *
 * This function takes a Double value representing a raw amount of currency,
 * and returns a String representing that amount formatted appropriately as
 * currency for the current locale of the device running the code.
 *
 * @param [amount] The raw amount of currency as a Double
 * @return A string representing that amount in the appropriate currency
 *         format for the device's current locale.
 */
actual fun Double?.toCurrency(): String {
    val numberFormat = NumberFormat.getCurrencyInstance()
    numberFormat.currency = Currency.getInstance(Locale.getDefault())
    return numberFormat.format(this ?: 0.0)
}