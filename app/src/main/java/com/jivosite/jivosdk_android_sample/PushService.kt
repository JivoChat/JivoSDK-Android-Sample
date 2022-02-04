package com.jivosite.jivosdk_android_sample

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.jivosite.jivosdk_android_sample.support.getPendingIntentFlags
import com.jivosite.jivosdk_android_sample.support.getUriNotificationSound
import com.jivosite.jivosdk_android_sample.ui.MainActivity
import com.jivosite.sdk.Jivo

/**
 * Created on 4/20/21.
 *
 * @author Aleksandr Tavtorkin (tavtorkin@jivosite.com)
 */
class PushService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        if (!Jivo.handleRemoteMessage(remoteMessage)) {
            remoteMessage.data["data"]?.let {
                sendNotification(it)
            }
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Jivo.updatePushToken(token)
    }

    private fun sendNotification(messageBody: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent, getPendingIntentFlags()
        )

        val channelId = getString(R.string.default_notification_channel_id)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_notification_small)
            .setColor(ContextCompat.getColor(applicationContext, R.color.midnight))
            .setContentTitle(getString(R.string.fcm_message))
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(getUriNotificationSound(applicationContext))
            .setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }

        notificationManager.notify(0, notificationBuilder.build())
    }

    @SuppressLint("NewApi")
    fun createNotificationChannel() {
        val channelId = getString(R.string.default_notification_channel_id)
        val name = applicationContext.getString(R.string.notification_channel_message_name)
        val description =
            applicationContext.getString(R.string.notification_channel_message_description)
        val importance = NotificationManager.IMPORTANCE_HIGH

        val channel = NotificationChannel(channelId, name, importance).also {
            it.description = description
            it.setSound(
                getUriNotificationSound(applicationContext),
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build()
            )
        }
        NotificationManagerCompat.from(applicationContext).createNotificationChannel(channel)
    }
}