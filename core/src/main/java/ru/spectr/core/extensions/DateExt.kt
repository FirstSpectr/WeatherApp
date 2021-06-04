package ru.spectr.core.extensions

import java.text.SimpleDateFormat
import java.util.*

const val FORMAT_dd_MMMM = "dd MMMM"

fun String.toDate(pattern: String) = SimpleDateFormat(pattern, Locale.getDefault()).parse(this) ?: Date()

fun Date.format(pattern: String): String = SimpleDateFormat(pattern, Locale.getDefault()).format(this)