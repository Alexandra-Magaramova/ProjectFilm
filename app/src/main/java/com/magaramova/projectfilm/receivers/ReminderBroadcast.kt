package com.magaramova.projectfilm.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.magaramova.projectfilm.data.Entity.Film
import com.magaramova.projectfilm.view.notification.NotificationConstants
import com.magaramova.projectfilm.view.notification.NotificationHelper

class ReminderBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val bundle = intent?.getBundleExtra(NotificationConstants.FILM_BUNDLE_KEY)
        val film: Film = bundle?.get(NotificationConstants.FILM_KEY) as Film

        NotificationHelper.createNotification(context!!, film)
    }
}