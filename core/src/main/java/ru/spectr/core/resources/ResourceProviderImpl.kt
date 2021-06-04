package ru.spectr.core.resources

import android.content.Context
import toothpick.InjectConstructor

@InjectConstructor
class ResourceProviderImpl(private val context: Context) : ResourceProvider {
    override fun getString(resId: Int, vararg formatArgs: Any): String {
        return context.getString(resId, *formatArgs)
    }
}
