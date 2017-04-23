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
import android.util.Log
import net.vnnz.apps.kotlin.tracker.utils.ImageUtils
import net.vnnz.apps.kotlin.tracker.view.ListItemsView
import net.vnnz.apps.kotlin.tracker.worker.DataLoader
import android.content.Intent
import net.vnnz.apps.kotlin.tracker.worker.WorkerService


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
        return DataLoader(view.getViewContext()!!, R.raw.test_map)
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

    fun saveSelectedImageMap() {
       /* val bitmap = ImageUtils.fillImageMap(view.getViewContext()!!, selectedItems.toTypedArray());
        ImageUtils.saveImage(bitmap, "europe");*/

        val workerService = Intent(view.getViewContext()!!, WorkerService::class.java)
        workerService.setAction(TrackerTasks.ACTION_COLOR_ANS_SAVE_MAP)
       // workerService.putParcelableArrayListExtra("vdv", selectedItems)
        view.getViewContext()?.startService(workerService)
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


