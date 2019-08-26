package com.vuongquocthanh.cloudmessage

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationHelper(val context: Context) {
    private lateinit var mNotificationManager: NotificationManager
    private lateinit var mBuilder: NotificationCompat.Builder

    companion object {
        const val NOTIFICATION_CHANNEL_ID = "10001"
    }

    fun createMessageNotification(title: String, message: String) {
        val resultIntent = Intent(context, ResultActivity::class.java)
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        mBuilder = NotificationCompat.Builder(context)
        mBuilder.setSmallIcon(R.mipmap.ic_launcher)
        mBuilder.setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(false)
                .setContentIntent(resultPendingIntent)
                .setAutoCancel(true)

        mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val audioAttributes = AudioAttributes.Builder()
//                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
//                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
//                    .build()

            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED

//            notificationChannel.setSound(soundUri, audioAttributes)

            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID)
            mNotificationManager.createNotificationChannel(notificationChannel)
        }
        mNotificationManager.notify(1 /* Request Code */, mBuilder.build())
    }

//    private fun playNotificationSound() {
//        try {
//            val r = RingtoneManager.getRingtone(context, soundUri)
//            r.play()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//
//    }
}