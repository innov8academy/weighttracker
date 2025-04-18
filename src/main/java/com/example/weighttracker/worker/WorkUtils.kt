package com.example.weighttracker.worker

import android.content.Context
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object WorkUtils {
    fun scheduleDailyReminder(context: Context) {
        val request = PeriodicWorkRequestBuilder<DailyReminderWorker>(24, TimeUnit.HOURS)
            .addTag("daily_reminder")
            .build()
        WorkManager.getInstance(context).enqueue(request)
    }
}