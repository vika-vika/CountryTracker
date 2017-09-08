package net.vnnz.apps.kotlin.tracker.activity

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View

import net.vnnz.apps.kotlin.tracker.view.ListItemsView
import kotlinx.android.synthetic.main.activity_list_items.*
import net.vnnz.apps.kotlin.tracker.ListPresenter
import net.vnnz.apps.kotlin.tracker.R
import net.vnnz.apps.kotlin.tracker.adapter.ItemSelectAdapter

class ListActivity : AppCompatActivity(), ListItemsView {

    private val presenter = ListPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_items)

        setSupportActionBar(toolbar)
        presenter.bindView(this)
    }

    override fun getViewContext(): Context? = this

    override fun getLoader(): LoaderManager = supportLoaderManager

    override fun onItemsLoaded() {
        itemsList.adapter = ItemSelectAdapter(presenter)
    }

    override fun updateHeader(size: Int) {
        toolbar.subtitle = "Selected $size countries"
    }

    fun onDoneClick(view: View) {
        presenter.saveSelectedImageMap(this);
    }

    override fun finishActivity() {
        setResult(Activity.RESULT_OK);
        finish();
    }

    private var dialog: ProgressDialog? = null

    override fun showLoadingUI(s: String) {
        dialog = ProgressDialog.show(this, "Please wait", s);
    }

    override fun hideLoadingUI() {
        if ((dialog != null) && (dialog?.isShowing!!)) {
            dialog?.dismiss();
            dialog = null;
        }
    }

    override fun onStop() {
        super.onStop()

        if (dialog != null) {
            dialog?.dismiss()
            dialog = null
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
