package net.vnnz.apps.kotlin.tracker.pojo

import android.os.Parcel
import android.os.Parcelable
import android.util.Log


data class ListItem(val item: Item) : Parcelable  {

    var isSelected: Boolean = false

    var isVisited: Boolean = false

    var name = item.name

    var key = item.key

    var color = item.color

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<ListItem> = object : Parcelable.Creator<ListItem> {
            override fun createFromParcel(source: Parcel): ListItem = createItem(source)
            override fun newArray(size: Int): Array<ListItem?> = arrayOfNulls(size)
        }

        fun createItem(source: Parcel): ListItem {
            val item = Item()
            item.name = source.readString()
            item.key = source.readString()
            item.color = source.readString()
            val listItem = ListItem(item)
            listItem.isSelected = (source.readByte().toInt() == 1)
            listItem.isVisited = (source.readByte() == (1.toByte()))

            return listItem
        }

    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(name)
        dest?.writeString(key)
        dest?.writeString(color)
        dest?.writeByte(if (isSelected) 1 else 0)
        dest?.writeByte(if (isVisited) 1 else 0)
    }


    override fun describeContents() = 0

    override fun toString(): String {
        return "ListItem(name=$name, key=$key, color=$color, isSelected=$isSelected, isVisited=$isVisited)"
    }

    fun getCountryItem(): Item {
        return Item(key, name);
    }


}