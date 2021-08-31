package xyz.beathub.beathub_android

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import xyz.beathub.beathub_android.databinding.ActivityMainBinding
import xyz.beathub.beathub_android.modules.ApiModule

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ApiModule.initRetrofit(getPreferences(Context.MODE_PRIVATE),this)
    }

}
