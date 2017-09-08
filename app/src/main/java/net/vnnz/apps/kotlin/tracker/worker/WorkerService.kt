package net.vnnz.apps.kotlin.tracker.worker

import android.app.IntentService
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import net.vnnz.apps.kotlin.tracker.TrackerTasks

import net.vnnz.apps.kotlin.tracker.pojo.ListItem


class WorkerService : IntentService("WorkerService") {

    override fun onHandleIntent(intent: Intent?) {
        val action = intent?.action
        TrackerTasks.executeTask(this, action, intent)
        LocalBroadcastManager.getInstance(this).sendBroadcast(Intent(TrackerTasks.WORK_COMPLETE_ACTION))
    }
}