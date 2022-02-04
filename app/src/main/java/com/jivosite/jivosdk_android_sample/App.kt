package com.jivosite.jivosdk_android_sample

import android.app.Application
import android.app.PendingIntent
import android.content.Intent
import com.jivosite.jivosdk_android_sample.support.getPendingIntentFlags
import com.jivosite.jivosdk_android_sample.support.getUriNotificationSound
import com.jivosite.jivosdk_android_sample.ui.MainActivity
import com.jivosite.jivosdk_android_sample.ui.MainActivity.Companion.EXTRA_TARGET
import com.jivosite.jivosdk_android_sample.ui.MainActivity.Companion.TARGET_CHAT
import com.jivosite.sdk.Jivo
import com.jivosite.sdk.push.handler.delegates.TextMessageDelegate
import com.jivosite.sdk.support.builders.Config

/**
 * Created on 4/19/21.
 *
 * @author Aleksandr Tavtorkin (tavtorkin@jivosite.com)
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Jivo.init(
            appContext = this,
            widgetId = "4UoDFh5U7n"
        )
        Jivo.setConfig(
            Config.Builder()
                .setLogo(R.drawable.vic_jivosdk_logo)
                .setBackground(R.drawable.bg_jivosdk_appbar)
                .setTitle(R.string.jivosdk_title)
                .setTitleTextColor(R.color.white)
                .setSubtitle(R.string.jivosdk_subtitle)
                .setSubtitleTextColor(R.color.white)
                .setSubtitleTextColorAlpha(0.6f)
                .setWelcomeMessage(R.string.jivosdk_welcome)
                .setOutgoingMessageColor(Config.Color.GREY)
                .setUriNotificationSound(getUriNotificationSound(applicationContext))
                .setOpenNotification {
                    PendingIntent.getActivity(
                        this,
                        TextMessageDelegate.NOTIFICATION_REQUEST_CODE,
                        Intent(this, MainActivity::class.java).apply {
                            putExtra(EXTRA_TARGET, TARGET_CHAT)
                        }, getPendingIntentFlags()
                    )
                }
                .build()
        )
    }
}