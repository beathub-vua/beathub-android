package xyz.beathub.beathub_android

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService

private const val JOB_ID = 1

class BeathubService : JobIntentService() {
    override fun onHandleWork(intent: Intent) {
//        RecenzoFetcher(this).fetchItems()
    }
    companion object { // convenience
        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, BeathubService::class.java, JOB_ID, intent)
        }
    }
}