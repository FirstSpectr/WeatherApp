package ru.spectr.core.extensions

import java.text.SimpleDateFormat
import java.util.*

const val FORMAT_dd_MMMM = "dd MMMM"
const val FORMAT_HH_mm = "HH:mm"
const val FORMAT_yyyy_MM_dd = "yyyy/MM/dd"

fun String.toTime(pattern: String) = SimpleDateFormat(pattern, Locale.getDefault()).parse(this)?.time ?: 0L

fun Long.format(pattern: String): String = SimpleDateFormat(pattern, Locale.getDefault()).format(this)