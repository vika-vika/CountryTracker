package net.vnnz.apps.kotlin.tracker.pojo

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "countries")
class Item() {

    @PrimaryKey(autoGenerate = true)
    var uid = 0

    @SerializedName("key")
    var key: String? = null

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String? = null

    @Ignore
    @SerializedName("color")
    var color: String? = null

    @Ignore
    var isSelected: Boolean = false

    @Ignore
    var isVisited: Boolean = false


    @Ignore
    constructor(key: String?, name: String?) : this() {

        this.key = key
        this.name = name
    }

    override fun toString(): String {
        return "ListItem(name=$name, key=$key, color=$color, isSelected=$isSelected, isVisited=$isVisited)"
    }
}



/*  companion object {
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


  override fun describeContents() = 0*/