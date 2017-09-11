package net.vnnz.apps.kotlin.tracker.pojo

import com.google.gson.annotations.SerializedName
import kotlin.collections.ArrayList

class JSONResult {

    @SerializedName("Countries")
    val items: ArrayList<Item> = ArrayList()

    override fun toString(): String {
        return "JSONResult(items=$items)"
    }

}