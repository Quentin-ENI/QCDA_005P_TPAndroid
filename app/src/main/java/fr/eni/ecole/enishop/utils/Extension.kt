package fr.eni.ecole.enishop.utils

import java.text.SimpleDateFormat
import java.util.Date

fun Date.toFrenchFormat(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(this)
}

fun Double.toPriceFormat(): String {
    return String.format("%.2f", this)
}