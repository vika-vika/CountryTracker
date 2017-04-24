package net.vnnz.apps.kotlin.tracker.presenter

import android.content.BroadcastReceiver
import android.content.Intent
import android.os.Environment
import android.view.View
import net.vnnz.apps.kotlin.tracker.view.MainView
import java.io.File

class MainPresenter {
    init {
        val root: String = Environment.getExternalStorageDirectory().absolutePath + "/maps/";
    }

    lateinit var mainView: MainView;

    fun bindView(_view: MainView) {
        mainView = _view

        mainView.getFloatingButton().setOnClickListener(View.OnClickListener { view ->
            mainView.startListActivity();
        })
    }

    fun  onReceiveEvent(intent: Intent) {
        mainView.updateMaps()
    }
}