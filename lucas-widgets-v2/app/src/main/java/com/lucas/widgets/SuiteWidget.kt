package com.lucas.widgets

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import java.text.SimpleDateFormat
import java.util.*

class SuiteWidget : AppWidgetProvider() {
    override fun onUpdate(context: Context, mgr: AppWidgetManager, ids: IntArray) {
        ids.forEach { id ->
            val views = RemoteViews(context.packageName, R.layout.widget_suite)
            val now = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
            views.setTextViewText(R.id.suite_time, now)

            val intent = Intent(context, FullscreenActivity::class.java).apply {
                putExtra("app", "suite")
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            val pi = PendingIntent.getActivity(context, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
            views.setOnClickPendingIntent(R.id.widget_suite_root, pi)

            mgr.updateAppWidget(id, views)
        }
    }
}
