package net.vnnz.apps.kotlin.tracker.pojo

import com.google.gson.annotations.SerializedName
import java.util.*

class JSONResult {

    @SerializedName("Countries")
    val items: Array<Item>? = null

    override fun toString(): String {
        return "JSONResult(items=${Arrays.toString(items)})"
    }


}