package net.vnnz.apps.kotlin.tracker

import android.content.Context
import net.vnnz.apps.kotlin.tracker.utils.ImageUtils

class TrackerTasks {

    companion object {
        val ACTION_COLOR_ANS_SAVE_MAP = "ACTION_COLOR_ANS_SAVE_MAP";

        fun executeTask(context: Context, action: String) {
            if (ACTION_COLOR_ANS_SAVE_MAP.equals(action)) {
                prepareAndSaveMapToFile(context)
            }
        }

        private fun prepareAndSaveMapToFile(context: Context) {
           // val bitmap = ImageUtils.fillImageMap(context, selectedItems.toTypedArray());
           // ImageUtils.saveImage(bitmap, "europe");
        }
    }
}