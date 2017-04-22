package net.vnnz.apps.kotlin.tracker.pojo

import com.google.gson.annotations.SerializedName

class JSONResult {

    @SerializedName("Countries")
    val JSONobject: Items? = null

    override fun toString(): String {
        return "ClassPojo [Object = $JSONobject]"
    }
}