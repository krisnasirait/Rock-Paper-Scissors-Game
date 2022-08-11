package com.krisna.rockpaperscissors.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.krisna.rockpaperscissors.databinding.ActivityMenuBinding
import java.util.*

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val name = intent.getStringExtra("userName")
        val str = name?.split(" ")
            ?.joinToString(separator = " ") { it ->
                it.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            } }

        binding.titleMenuFirst.text = "$str vs Pemain"
        binding.titleMenuSecond.text = "$str vs CPU"
    }
}