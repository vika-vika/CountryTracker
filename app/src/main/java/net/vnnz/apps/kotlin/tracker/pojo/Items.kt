package net.vnnz.apps.kotlin.tracker.pojo

import com.google.gson.annotations.SerializedName

class Items {

    @SerializedName("Country")
    val items: Array<Item>? = null

    override fun toString(): String {
        return "ClassPojo [Items = $items]"
    }
}