package net.vnnz.apps.kotlin.tracker.pojo

import com.google.gson.annotations.SerializedName

class Item {

    @SerializedName("key")
    var key: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("color")
    var color: String? = null

    override fun toString(): String {
        return "Item_{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                '}'
    }
}