package net.vnnz.apps.kotlin.tracker.view

import android.support.design.widget.FloatingActionButton

interface MainView {
    fun getFloatingButton() : FloatingActionButton

    fun startListActivity()
}