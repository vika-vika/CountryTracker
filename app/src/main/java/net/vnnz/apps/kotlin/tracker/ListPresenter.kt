package net.vnnz.apps.kotlin.tracker

import android.databinding.BindingAdapter
import android.graphics.Color
import android.widget.ImageView
import android.widget.ListView
import android.widget.RelativeLayout
import net.vnnz.apps.kotlin.tracker.adapter.ItemSelectAdapter
import net.vnnz.apps.kotlin.tracker.pojo.ListItem
import net.vnnz.apps.kotlin.tracker.utils.ResourceUtils


@BindingAdapter("bind:itemsList")
fun bindItemsList(view: ListView, items: List<ListItem>) {
    val adapter = ItemSelectAdapter(items)
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


class ListPresenter {

}