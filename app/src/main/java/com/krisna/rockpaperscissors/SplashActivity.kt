package com.krisna.rockpaperscissors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.krisna.rockpaperscissors.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.imageLogotext.load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
    }
}