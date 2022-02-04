package com.jivosite.jivosdk_android_sample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.jivosite.jivosdk_android_sample.R

/**
 * Created on 4/19/21.
 *
 * @author Aleksandr Tavtorkin (tavtorkin@jivosite.com)
 */
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    companion object {
        const val EXTRA_TARGET = "ExtraTarget"
        const val TARGET_CHAT = "TargetChat"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController

        if (intent.getStringExtra(EXTRA_TARGET) == TARGET_CHAT) {
            navController.navigate(R.id.action_pageMain_to_jivoChat)
        }
    }
}
