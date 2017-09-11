package net.vnnz.apps.kotlin.tracker

import android.content.Context
import android.databinding.BindingAdapter
import android.databinding.ObservableArrayList
import android.graphics.Color
import android.widget.ImageView
import android.widget.RelativeLayout
import net.vnnz.apps.kotlin.tracker.utils.ResourceUtils
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader

import net.vnnz.apps.kotlin.tracker.view.ListItemsView
import net.vnnz.apps.kotlin.tracker.worker.DataLoader
import android.util.Log
import co.metalab.asyncawait.async
import net.vnnz.apps.kotlin.tracker.dao.CountriesDatabase
import net.vnnz.apps.kotlin.tracker.pojo.Item
import net.vnnz.apps.kotlin.tracker.utils.ImageUtils

import java.util.ArrayList


class ListPresenter() : LoaderManager.LoaderCallbacks<List<Item>> {

    lateinit var view: ListItemsView;

    var selectedItems: ArrayList<Item> = ArrayList<Item>()
    var items: ObservableArrayList<Item> = ObservableArrayList<Item>()

    fun onCheckboxClick(item: Item) {

        if (selectedItems.contains(item))
            selectedItems.remove(item)
        else
            selectedItems.add(item)
        view.updateHeader(selectedItems.size)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<List<Item>> {
        return DataLoader(view.getViewContext()!!, R.raw.test_map)
    }

    override fun onLoadFinished(loader: Loader<List<Item>>?, data: List<Item>?) {
        for (item in data!!) {
            items.add(item)
        }
        view.onItemsLoaded()
    }

    override fun onLoaderReset(loader: Loader<List<Item>>?) {

    }

    fun bindView(_view: ListItemsView) {
        view = _view
        view.getLoader().initLoader(0, null, this)
    }

    fun saveSelectedImageMap(context: Context) {

        async {
            view.showLoadingUI("Preparing map");

            await {
                val bitmap = ImageUtils.fillImageMap(context, selectedItems?.toTypedArray())
                ImageUtils.saveImage(bitmap, "europe")
                val db : CountriesDatabase = CountriesDatabase.getAppDatabase(context);

                val foo = ArrayList<Item>();
                selectedItems.forEach {
                    i-> foo.add(Item(i.key, i.name))
                }

                db.countryDao().insertAll(*foo.toTypedArray());

                Log.e("!!!!", "!!! " + db.countryDao().all.size )
                Log.e("!!!!", "!!! " + db.countryDao().all.get(0).toString() )
            }

            view.hideLoadingUI();
            view.finishActivity();
        }
    }
}

@BindingAdapter("bind:visitedIcon")
fun getVisitedIcon(view: ImageView, isVisited: Boolean) {
    if (isVisited)
        view.setImageDrawable(ResourceUtils.getColoredIcon(view.context))
}

@BindingAdapter("bind:backgroundColor")
fun setBackgroundColor(view: RelativeLayout, isVisited: Boolean) {

    if (isVisited) {
        val color = ResourceUtils.getColor(R.attr.colorAccent, view.context)
        val colorWithAplha = Color.argb(64, Color.red(color), Color.green(color), Color.blue(color))
        view.setBackgroundColor(colorWithAplha)
    }
}


