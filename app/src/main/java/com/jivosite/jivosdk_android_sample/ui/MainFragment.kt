package com.jivosite.jivosdk_android_sample.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jivosite.jivosdk_android_sample.R

/**
 * Created on 4/19/21.
 *
 * @author Aleksandr Tavtorkin (tavtorkin@jivosite.com)
 */
class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.jivoBtn)?.run {
            setOnClickListener {
                findNavController().navigate(R.id.action_pageMain_to_jivoChat)
            }
        }
    }
}