package com.jivosite.jivosdk_android_sample.support

import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Build
import com.jivosite.jivosdk_android_sample.R

/**
 * Created on 03.02.2022.
 *
 * @author Aleksandr Tavtorkin (tavtorkin@jivosite.com)
 */

fun getUriNotificationSound(applicationContext: Context): Uri =
    Uri.parse("${ContentResolver.SCHEME_ANDROID_RESOURCE}://${applicationContext.packageName}/${R.raw.jivo_tip}")

fun getPendingIntentFlags(): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    } else {
        PendingIntent.FLAG_UPDATE_CURRENT
    }
}
