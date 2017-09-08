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
    constructor(key: String?, name: String?) : this() {

        this.key = key
        this.name = name
    }

    override fun toString(): String {
        return "Item_{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                '}'
    }
}