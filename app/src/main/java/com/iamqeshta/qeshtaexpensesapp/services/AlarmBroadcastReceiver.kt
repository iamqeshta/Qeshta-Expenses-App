package com.iamqeshta.qeshtaexpensesapp.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.iamqeshta.qeshtaexpensesapp.R
import com.iamqeshta.qeshtaexpensesapp.ui.activities.SplashActivity
import java.util.*

class AlarmBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        showNotification(context)
    }

    private fun showNotification(context: Context) {
        val channelId = "notification"
        val name: CharSequence = context.resources.getString(R.string.app_name)
        val notificationIntent = Intent(context, SplashActivity::class.java)
        val bundle = Bundle()
        notificationIntent.putExtras(bundle)
        notificationIntent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        val contentIntent = PendingIntent.getActivity(
            context,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notification =
            Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context.packageName + "/" + R.raw.alert_notification)
        var importance = 0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            importance = NotificationManager.IMPORTANCE_HIGH
        }
        var mChannel: NotificationChannel? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = NotificationChannel(channelId, name, importance)
            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()
            mChannel.enableLights(true)
            mChannel.enableVibration(true)
            mChannel.setSound(notification, audioAttributes)
        }
        val builder = NotificationCompat.Builder(context, channelId)
        builder.setLargeIcon(
            BitmapFactory.decodeResource(
                context.resources,
                R.mipmap.ic_launcher
            )
        )
        builder.setContentTitle(context.getString(R.string.save_your_money))
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setContentText(context.getString(R.string.notifications_message))
        builder.setAutoCancel(true)
        builder.priority = NotificationCompat.PRIORITY_DEFAULT
        builder.setDefaults(Notification.DEFAULT_LIGHTS)
        builder.setSound(notification)
        builder.setContentIntent(contentIntent)
        builder.setChannelId(channelId)
        val note = builder.build()
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Objects.requireNonNull(manager).createNotificationChannel(mChannel!!)
        }
        Objects.requireNonNull(manager).notify(1, note)
    }
}