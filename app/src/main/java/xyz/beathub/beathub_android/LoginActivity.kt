package xyz.beathub.beathub_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        setContentView(R.layout.login_activity)
//setContentView(R.layout.activity_main)
//        initHamburgerMenu()
//        initNavigation()
    }
}