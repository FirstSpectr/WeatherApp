package ru.spectr.core.resources

import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String
}
