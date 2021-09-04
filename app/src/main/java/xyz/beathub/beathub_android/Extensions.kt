package xyz.beathub.beathub_android

import android.app.Activity
import android.content.*

import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Handler
import android.os.Looper
import android.util.Patterns
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast

const val USER_AUTH_KEY = "USER_AUTH_KEY"

fun backgroundThreadShortToast(
    context: Context?,
    msg: String?
) {
    if (context != null && msg != null) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(
                context,
                msg,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

val URL = "https://privateip1337.lets.ee:8443/beathub"

fun CharSequence?.isValidEmail() = !(!isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches())

fun View.applyAnimation(resourceId: Int) =
    startAnimation(AnimationUtils.loadAnimation(context, resourceId))

inline fun<reified T: Activity> Context.startActivity() = startActivity(Intent(this, T::class.java).addFlags(
    Intent.FLAG_ACTIVITY_NEW_TASK))

inline fun<reified T: Activity> Context.startActivity(key: String, value: Int) = startActivity(Intent(this, T::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).apply { putExtra(key, value) })
inline fun<reified T: Activity> Context.startActivity(key: String, value: String) = startActivity(Intent(this, T::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).apply { putExtra(key, value) })


inline fun<reified T: BroadcastReceiver> Context.sendBroadcast() = sendBroadcast(Intent(this, T::class.java))




fun Context.isOnline() : Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork
    if (network != null) {
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        if (networkCapabilities != null) {
            return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        }
    }
    return false
}
