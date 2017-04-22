package net.vnnz.apps.kotlin.tracker

import android.databinding.BindingAdapter
import android.databinding.ObservableArrayList
import android.graphics.Color
import android.widget.ImageView
import android.widget.RelativeLayout
import net.vnnz.apps.kotlin.tracker.pojo.ListItem
import net.vnnz.apps.kotlin.tracker.utils.ResourceUtils
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import net.vnnz.apps.kotlin.tracker.pojo.Item
import net.vnnz.apps.kotlin.tracker.view.ListItemsView

class ListPresenter() : LoaderManager.LoaderCallbacks<List<ListItem>> {

    lateinit var view: ListItemsView;

    var selectedItems: MutableList<ListItem> = mutableListOf<ListItem>()
    var items: ObservableArrayList<ListItem> = ObservableArrayList<ListItem>()

    fun onCheckboxClick(item: ListItem) {

        if (selectedItems.contains(item))
            selectedItems.remove(item)
        else
            selectedItems.add(item)
        view.updateHeader(selectedItems.size);
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<List<ListItem>> {
        return DataLoader(view.getViewContext()!!, R.raw.europe)
    }

    override fun onLoadFinished(loader: Loader<List<ListItem>>?, data: List<ListItem>?) {
        for (item in data!!) {
            items.add(item);
        }
        view.onItemsLoaded()
    }

    override fun onLoaderReset(loader: Loader<List<ListItem>>?) {

    }

    fun bindView(_view: ListItemsView) {
        view = _view
        view.getLoader().initLoader(0, null, this)
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


