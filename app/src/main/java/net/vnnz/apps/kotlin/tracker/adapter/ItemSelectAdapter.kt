package net.vnnz.apps.kotlin.tracker.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import net.vnnz.apps.kotlin.tracker.R
import kotlinx.android.synthetic.main.row_select_item.view.*
import net.vnnz.apps.kotlin.tracker.pojo.ListItem

import android.graphics.Color
import net.vnnz.apps.kotlin.tracker.databinding.RowSelectItemBinding
import net.vnnz.apps.kotlin.tracker.utils.ResourceUtils


class ItemSelectAdapter(var items: List<ListItem>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

        val inflater:LayoutInflater =  viewGroup?.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding : RowSelectItemBinding  = DataBindingUtil.inflate(inflater, R.layout.row_select_item, viewGroup, false);
        binding.item = items.get(position);
        return binding.root;


        /*val view = convertView ?: LayoutInflater.from(viewGroup?.context).inflate(R.layout.row_select_item, viewGroup, false)
        var holder = ViewHolder(view)
        holder.bindData(items.get(position))
        return view*/
    }

    override fun getItem(position: Int)= items?.get(position)

    override fun getCount() = items.size

    override fun getItemId(position: Int) = getItem(position).hashCode().toLong()

    class ViewHolder(val view: View)  {

        fun bindData(dataItem : ListItem){
            view.itemName.text = dataItem.name

            if (dataItem.isVisited) {
                view.checkboxSelected.visibility = View.GONE;
                view.iconVisited.visibility      = View.VISIBLE

                val color = ResourceUtils.getColor(R.attr.colorAccent, view.context)
                val colorWithAplha = Color.argb(64, Color.red(color), Color.green(color), Color.blue(color))

                view.layout.setBackgroundColor(colorWithAplha)
                view.iconVisited.setImageDrawable(ResourceUtils.getColoredIcon(view.context))

            } else {
                view.checkboxSelected.visibility = View.VISIBLE;
                view.iconVisited.visibility      = View.GONE

                view.checkboxSelected.isChecked = dataItem.isSelected
                view.checkboxSelected.tag = dataItem.key

                view.checkboxSelected.setOnClickListener(View.OnClickListener { view ->
                    //val isChecked = (view as CheckBox).isChecked()
                })
            }

        }

    }

}

