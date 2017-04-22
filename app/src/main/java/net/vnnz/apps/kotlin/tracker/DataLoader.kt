package net.vnnz.apps.kotlin.tracker

import android.content.Context
import android.support.v4.content.AsyncTaskLoader

import com.google.gson.Gson

import net.vnnz.apps.kotlin.tracker.pojo.JSONResult
import net.vnnz.apps.kotlin.tracker.pojo.ListItem

import java.io.InputStreamReader
import java.util.ArrayList

class DataLoader(context: Context, internal var resId: Int) : AsyncTaskLoader<List<ListItem>>(context) {

    init {
        onContentChanged()
    }

    override fun loadInBackground(): List<ListItem> {
        val inputStream = context.resources.openRawResource(resId)
        val reader = InputStreamReader(inputStream)
        val response = Gson().fromJson(reader, JSONResult::class.java)
        val items = response.JSONobject?.items
        return items?.map(::ListItem) ?: ArrayList() // ::ListItem -> ListItem(it)
    }

    override fun onStartLoading() {
        if (takeContentChanged())
            forceLoad()
    }

    override fun onStopLoading() {
        cancelLoad()
    }

}