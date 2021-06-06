package ru.spectr.weatherapp.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DayArguments(
    val woeid: Int,
    val date: String,
    val title: String
) : Parcelable