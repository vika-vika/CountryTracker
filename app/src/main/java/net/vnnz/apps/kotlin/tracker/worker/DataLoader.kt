package net.vnnz.apps.kotlin.tracker.worker

import android.content.Context
import android.support.v4.content.AsyncTaskLoader

import com.google.gson.Gson
import net.vnnz.apps.kotlin.tracker.pojo.Item

import net.vnnz.apps.kotlin.tracker.pojo.JSONResult

import java.io.InputStreamReader

class DataLoader(context: Context, private var resId: Int) : AsyncTaskLoader<List<Item>>(context) {

    init {
        onContentChanged()
    }

    override fun loadInBackground(): List<Item> {
        val inputStream = context.resources.openRawResource(resId)
        val reader = InputStreamReader(inputStream)
        val result = Gson().fromJson(reader, JSONResult::class.java)
        val items = result.items
        return items
    }

    override fun onStartLoading() {
        if (takeContentChanged())
            forceLoad()
    }

    override fun onStopLoading() {
        cancelLoad()
    }

}
