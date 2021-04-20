package com.jivosite.jivosdk_android_sample

import android.app.Application
import com.jivosite.sdk.Jivo

/**
 * Created on 4/19/21.
 *
 * @author Alexander Tavtorkin (av.tavtorkin@gmail.com)
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Jivo.init(
            appContext = this,
            siteId = 251342,
            widgetId = "PJp64kxPS9",
            host = "andro1d.dev.jivosite.com",
            port = "1443"
        )
    }
}