package net.vnnz.apps.kotlin.tracker

import android.content.Context
import android.databinding.BindingAdapter
import android.graphics.Color
import android.util.Log
import android.widget.ImageView
import android.widget.ListView
import android.widget.RelativeLayout
import net.vnnz.apps.kotlin.tracker.adapter.ItemSelectAdapter
import net.vnnz.apps.kotlin.tracker.pojo.ListItem
import net.vnnz.apps.kotlin.tracker.utils.ResourceUtils
import android.databinding.ObservableField
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringWriter


@BindingAdapter("bind:listViewModel")
fun bindViewModel(view: ListView, viewmodel : ListPresenter) {
    val adapter = ItemSelectAdapter(viewmodel)
    view.adapter = adapter
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

class ListPresenter(var context: Context) {

    var selectedItems : MutableList<ListItem> = mutableListOf<ListItem>()
    val items : List<ListItem> = Data.getInstance().items.map { ListItem(it) }

    val itemsCount = ObservableField<String>()

    init {
        itemsCount.set("0");
    }

    fun onCheckboxClick (item : ListItem) {

        if (selectedItems.contains(item)) {
            selectedItems.remove(item)
        } else {
            selectedItems.add(item)
        }
        itemsCount.set(selectedItems.size.toString())
      //  Log.e("dfs", ResourceUtils.readJSONfromRaw(R.raw.europe, context))
    }

}