package net.vnnz.apps.kotlin.tracker

import android.content.Context
import android.content.Intent
import net.vnnz.apps.kotlin.tracker.pojo.ListItem
import net.vnnz.apps.kotlin.tracker.utils.ImageUtils

class TrackerTasks {

    companion object {
        val ACTION_COLOR_ANS_SAVE_MAP = "ACTION_COLOR_ANS_SAVE_MAP";
        val EXTRA_COLOR_ANS_SAVE_MAP = "EXTRA_COLOR_ANS_SAVE_MAP";

        fun executeTask(context: Context, action:String?, intent: Intent?) {
            if (ACTION_COLOR_ANS_SAVE_MAP.equals(action)) {
                val items = intent?.getParcelableArrayListExtra<ListItem>(TrackerTasks.EXTRA_COLOR_ANS_SAVE_MAP)
                prepareAndSaveMapToFile(context, items)
            }
        }

        private fun prepareAndSaveMapToFile(context: Context, items: ArrayList<ListItem>?) {
            val bitmap = ImageUtils.fillImageMap(context, items?.toTypedArray())
            ImageUtils.saveImage(bitmap, "europe")
           // val intent = Intent();
          //  intent.setAction("FILE_CHANGED");
          //  context.sendBroadcast(intent)
        }
    }
}