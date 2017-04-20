package net.vnnz.apps.kotlin.tracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import net.vnnz.apps.kotlin.tracker.R
import kotlinx.android.synthetic.main.list_row_select_country.view.*
import net.vnnz.apps.kotlin.tracker.pojo.ListItem

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable

import android.content.Context
import android.graphics.Color
import android.util.TypedValue


class ItemSelectAdapter(var items: List<ListItem>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(viewGroup?.context).inflate(R.layout.list_row_select_country, viewGroup, false)
        var holder = ViewHolder(view)
        holder.bindData(items.get(position))
        return view
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

                val color = getColor(R.attr.colorAccent, view.context)
                val colorWithAplha = Color.argb(64, Color.red(color), Color.green(color), Color.blue(color))

                view.layout.setBackgroundColor(colorWithAplha)
                view.iconVisited.setImageDrawable(getColoredIcon(view.context))

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

        private fun getColor(id: Int, context: Context): Int {
            val typedValue = TypedValue()
            val theme = context.getTheme()
            theme.resolveAttribute(id, typedValue, true)
            return typedValue.data
        }

        @Suppress("DEPRECATION")
        private fun getColoredIcon(context: Context): Drawable {
            val mDrawable = context.getResources().getDrawable(R.drawable.ic_done)
            mDrawable.colorFilter = PorterDuffColorFilter(getColor(R.attr.colorAccent, context), PorterDuff.Mode.MULTIPLY)
            return mDrawable
        }

    }

}

