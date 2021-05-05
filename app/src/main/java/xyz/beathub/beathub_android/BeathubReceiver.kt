package xyz.beathub.beathub_android

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import xyz.beathub.beathub_android.HostActivity


class BeathubReceiver : BroadcastReceiver() { // BroadcastReceiver aint no Context

    override fun onReceive(context: Context, intent: Intent) {

        context.startActivity<HostActivity>()
    }
}