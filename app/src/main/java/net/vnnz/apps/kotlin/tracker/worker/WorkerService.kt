package net.vnnz.apps.kotlin.tracker.worker

import android.app.IntentService
import android.content.Intent
import android.util.Log
import net.vnnz.apps.kotlin.tracker.TrackerTasks

import net.vnnz.apps.kotlin.tracker.pojo.ListItem


class WorkerService : IntentService("WaterReminderIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        val action = intent?.action
        TrackerTasks.executeTask(this, action, intent)
    }
}