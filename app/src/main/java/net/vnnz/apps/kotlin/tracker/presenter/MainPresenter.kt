package net.vnnz.apps.kotlin.tracker.presenter

import android.os.FileObserver
import android.view.View
import net.vnnz.apps.kotlin.tracker.view.MainView


class MainPresenter {

    lateinit var mainView: MainView;

    fun bindView(_view: MainView) {
        mainView = _view

        mainView.getFloatingButton().setOnClickListener(View.OnClickListener { view ->
            mainView.startListActivity();
        })
    }
}