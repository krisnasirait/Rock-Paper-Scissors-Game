package com.krisna.rockpaperscissors.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.krisna.rockpaperscissors.databinding.ActivitySplashBinding
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.imageLogotext.load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")

        activityScope.launch {
            delay(3000)
            val intent = Intent(this@SplashActivity, LandingActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }
}