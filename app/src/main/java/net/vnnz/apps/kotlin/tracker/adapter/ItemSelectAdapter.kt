package net.vnnz.apps.kotlin.tracker.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import net.vnnz.apps.kotlin.tracker.R
import net.vnnz.apps.kotlin.tracker.pojo.ListItem

import net.vnnz.apps.kotlin.tracker.ListPresenter
import net.vnnz.apps.kotlin.tracker.databinding.RowSelectItemBinding


class ItemSelectAdapter(var viewmodel : ListPresenter) : BaseAdapter() {
    var items : List<ListItem>

    init {
        items = viewmodel.items
    }

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

        val inflater:LayoutInflater =  viewGroup?.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding : RowSelectItemBinding  = DataBindingUtil.inflate(inflater, R.layout.row_select_item, viewGroup, false)
        binding.item = viewmodel.items.get(position)
        binding.viewmodel = viewmodel
        return binding.root;
    }

    override fun getItem(position: Int)= items?.get(position)

    override fun getCount() = items?.size

    override fun getItemId(position: Int) = getItem(position).hashCode().toLong()

}

