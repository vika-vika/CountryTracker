package net.vnnz.apps.kotlin.tracker

import android.databinding.BindingAdapter
import android.widget.ListView
import net.vnnz.apps.kotlin.tracker.adapter.ItemSelectAdapter
import net.vnnz.apps.kotlin.tracker.pojo.ListItem


@BindingAdapter("bind:itemsList")
fun bindItemsList(view: ListView, items: List<ListItem>) {
    val adapter = ItemSelectAdapter(items)
    view.adapter = adapter
}

class ListPresenter {

}