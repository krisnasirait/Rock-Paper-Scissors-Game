package com.krisna.rockpaperscissors

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.krisna.rockpaperscissors.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var layout: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layout = ActivityMainBinding.inflate(layoutInflater)
        val view = layout.root
        setContentView(view)

        layout.btnBatu.setOnClickListener {
            layout.btnBatu.setBackgroundResource(R.drawable.roundcorner)
        }

        layout.btnKertas.setOnClickListener {
            layout.btnKertas.setBackgroundResource(R.drawable.roundcorner)
        }

        layout.btnGunting.setOnClickListener {
            layout.btnGunting.setBackgroundResource(R.drawable.roundcorner)
        }
    }

    private fun getBotInput(): String{
        val options = arrayOf("Rock", "Paper", "Scissors")
        val botOptions: Int = Random.nextInt(options.size)
        return options[botOptions]
    }

    private fun checkResult(playerInput: String, comInput: String) {
        if (playerInput.equals(comInput, true)) {
            layout.textWin.text = "DRAW"
            layout.textMenang.text = ""
            layout.layoutWinner.setBackgroundColor(ContextCompat.getColor(this, R.color.blue))
        } else if (playerInput.equals("batu", true) && comInput.equals("gunting", true)
            || playerInput.equals("kertas", true) && comInput.equals("batu", true)
            || playerInput.equals("gunting", true) && comInput.equals("kertas", true)
        ) {
            layout.textWin.text = "Pemain 1"
            layout.textMenang.text = "MENANG!"
            layout.layoutWinner.setBackgroundColor(ContextCompat.getColor(this, R.color.light_green))
        } else if (comInput.equals("batu", true) && playerInput.equals("gunting", true)
            || comInput.equals("kertas", true) && playerInput.equals("batu", true)
            || comInput.equals("gunting", true) && playerInput.equals("kertas", true)
        ) {
            layout.textWin.text = "Pemain 2"
            layout.textMenang.text = "MENANG!"
            layout.layoutWinner.setBackgroundColor(ContextCompat.getColor(this, R.color.light_green))
        }
    }
}