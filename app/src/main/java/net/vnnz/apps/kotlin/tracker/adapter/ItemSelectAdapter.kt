package net.vnnz.apps.kotlin.tracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import net.vnnz.apps.kotlin.tracker.R
import kotlinx.android.synthetic.main.list_row_select_country.view.*
import net.vnnz.apps.kotlin.tracker.pojo.Item
import net.vnnz.apps.kotlin.tracker.pojo.ListItem
import java.util.ArrayList


class ItemSelectAdapter(var items: List<ListItem>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(viewGroup?.context).inflate(R.layout.list_row_select_country, viewGroup, false)
        val data = items.get(position)
        var holder = ViewHolder(view)
        holder.bindData(data)
        return view
    }

    override fun getItem(position: Int)= items?.get(position)

    override fun getCount() = items.size

    override fun getItemId(position: Int) = getItem(position).hashCode().toLong()

    class ViewHolder(val view: View)  {
        fun bindData(dataItem : ListItem){
            view.itemName.text = dataItem.name
        }

    }
}

