package net.vnnz.apps.kotlin.tracker.activity

import android.content.Context
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v7.app.AppCompatActivity
import android.util.Log

import net.vnnz.apps.kotlin.tracker.view.ListItemsView
import kotlinx.android.synthetic.main.activity_list_items.*
import net.vnnz.apps.kotlin.tracker.ListPresenter
import net.vnnz.apps.kotlin.tracker.R
import net.vnnz.apps.kotlin.tracker.adapter.ItemSelectAdapter
import net.vnnz.apps.kotlin.tracker.utils.ImageUtils

class ListActivity : AppCompatActivity(), ListItemsView {

    val presenter = ListPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_items)

        presenter.bindView(this)
        imageView.setImageBitmap(ImageUtils.fillImageMap(this, ""))
    }

    override fun getViewContext(): Context? = this

    override fun getLoader(): LoaderManager = supportLoaderManager

    override fun onItemsLoaded() {
       // Log.e("TAG", presenter.items.toString());
        itemsList.adapter = ItemSelectAdapter(presenter)
    }

    override fun updateHeader(size: Int) {
        Log.e("TAG", size.toString());
    }
}
