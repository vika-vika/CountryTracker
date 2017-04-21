package net.vnnz.apps.kotlin.tracker


import android.content.Context
import android.databinding.BaseObservable
import android.databinding.BindingAdapter
import android.databinding.ObservableArrayList
import android.graphics.Color
import android.util.Log
import android.widget.ImageView
import android.widget.ListView
import android.widget.RelativeLayout
import net.vnnz.apps.kotlin.tracker.adapter.ItemSelectAdapter
import net.vnnz.apps.kotlin.tracker.pojo.ListItem
import net.vnnz.apps.kotlin.tracker.utils.ResourceUtils
import android.databinding.ObservableField
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import net.vnnz.apps.kotlin.tracker.pojo.Item
import java.util.ArrayList


@BindingAdapter("bind:listViewModel")
fun bindViewModel(view: ListView, viewmodel : ListPresenter) {
    val adapter = ItemSelectAdapter(viewmodel)
    view.adapter = adapter
    Log.e("TAG", "!!!!!!!!!!!!!!!!2");
}


@BindingAdapter("bind:visitedIcon")
fun getVisitedIcon(view: ImageView, isVisited: Boolean) {
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

class ListPresenter(var context: Context, loaderManager: LoaderManager) : LoaderManager.LoaderCallbacks<List<ListItem>>  {

    var selectedItems : MutableList<ListItem> = mutableListOf<ListItem>()
    var items         : ObservableArrayList<ListItem> =  ObservableArrayList<ListItem>()

    val itemsCount    = ObservableField<String>()
    val adapter: ItemSelectAdapter

    init {
        itemsCount.set("0");
        loaderManager.initLoader(0, null, this)
        adapter = ItemSelectAdapter(this)
        // getList.setAdapter(adapter);
    }

    /*fun bindViewModel(view: ListView, viewmodel : ListPresenter) {
        val adapter = ItemSelectAdapter(viewmodel)
        view.adapter = adapter
        Log.e("TAG", "!!!!!!!!!!!!!!!!3");
    }*/

    fun onCheckboxClick (item : ListItem) {

        if (selectedItems.contains(item)) {
            selectedItems.remove(item)
        } else {
            selectedItems.add(item)
        }
        itemsCount.set(selectedItems.size.toString())

    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<List<ListItem>> {
         return DataLoader(context, R.raw.europe)
    }

    override fun onLoadFinished(loader: Loader<List<ListItem>>?, data: List<ListItem>?) {
        Log.e("TAG", "data".plus(data?.size));
        items.add(ListItem(Item("1111111", "11111111")))
        items.add(ListItem(Item("2211111", "22111111")))
        //notifyPro
    }

    override fun onLoaderReset(loader: Loader<List<ListItem>>?) {

    }
}