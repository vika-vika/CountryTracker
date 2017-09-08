package net.vnnz.apps.kotlin.tracker.view

import android.content.Intent
import android.graphics.Bitmap
import android.support.design.widget.FloatingActionButton

interface MainView {
    fun getFloatingButton() : FloatingActionButton

    fun startListActivity()
    fun startActivity(shareIntent: Intent?)
    fun updateMap(bit: Bitmap?)
}