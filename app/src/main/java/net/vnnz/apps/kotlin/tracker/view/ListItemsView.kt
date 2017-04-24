package net.vnnz.apps.kotlin.tracker.view

import android.content.Context
import android.support.v4.app.LoaderManager

interface ListItemsView {
    fun onItemsLoaded()
    fun getLoader (): LoaderManager
    fun getViewContext() : Context?
    fun updateHeader(size: Int)
    fun finishActivity()
}
