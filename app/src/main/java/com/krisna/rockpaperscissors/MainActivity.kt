package com.krisna.rockpaperscissors

import android.os.Bundle
import android.util.Log
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
            Log.d("User Input", "Batu")
            playerInput("", "Batu")
        }

        layout.btnKertas.setOnClickListener {
            Log.d("User Input", "Kertas")
            playerInput("", "Kertas")
        }

        layout.btnGunting.setOnClickListener {
            Log.d("User Input", "Gunting")
            playerInput("", "Gunting")
        }

        layout.btnRefresh.setOnClickListener {
            Log.d("User Input", "Button Refresh di Click")
            playerInput("", "Reset")
        }
    }

    private fun btnActivated() {
        layout.btnGunting.isEnabled = true
        layout.btnKertas.isEnabled = true
        layout.btnBatu.isEnabled = true
    }

    private fun btnDeactivated() {
        layout.btnGunting.isEnabled = false
        layout.btnKertas.isEnabled = false
        layout.btnBatu.isEnabled = false
    }

    private fun playerInput(textVersus: String, playerInput: String) {
        if (playerInput == "Batu") {
            layout.textVersus.text = textVersus
            layout.btnBatu.setBackgroundResource(R.drawable.roundcorner)
            checkResult("batu", getBotInput())
            btnDeactivated()
        } else if (playerInput == "Kertas") {
            layout.textVersus.text = textVersus
            layout.btnKertas.setBackgroundResource(R.drawable.roundcorner)
            checkResult("kertas", getBotInput())
            btnDeactivated()

        } else if (playerInput == "Gunting") {
            layout.textVersus.text = textVersus
            layout.btnGunting.setBackgroundResource(R.drawable.roundcorner)
            checkResult("gunting", getBotInput())
            btnDeactivated()
        } else if (buttonClicked == "Reset") {
            btnActivated()
            layout.textVersus.text = "VS"

            layout.btnBatu.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
            layout.btnKertas.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
            layout.btnGunting.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

            layout.btnBatuCom.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
            layout.btnKertasCom.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
            layout.btnGuntingCom.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

            layout.textWin.text = ""
            layout.textMenang.text = ""
            layout.textWin.setBackgroundResource(R.drawable.backround_white)
        }

    }

    private fun getBotInput(): String {
        val options = arrayOf("batu", "kertas", "gunting")
        val botOptions: Int = Random.nextInt(options.size)
        when {
            options[botOptions] == "batu" -> {
                Log.d("Bot Input", "Batu")
                layout.btnBatuCom.setBackgroundResource(R.drawable.roundcorner)
            }
            options[botOptions] == "kertas" -> {
                Log.d("Bot Input", "Kertas")
                layout.btnKertasCom.setBackgroundResource(R.drawable.roundcorner)
            }
            options[botOptions] == "gunting" -> {
                Log.d("Bot Input", "Gunting")
                layout.btnGuntingCom.setBackgroundResource(R.drawable.roundcorner)
            }
        }
        return options[botOptions]
    }

    private fun checkResult(playerInput: String, comInput: String) {
        if (playerInput.equals(comInput, true)) {
            Log.d("Hasil", "DRAW")
            layout.textWin.text = "DRAW"
            layout.textMenang.text = ""
            layout.textWin.setBackgroundResource(R.drawable.backround_draw)
        } else if (playerInput.equals("batu", true) && comInput.equals("gunting", true)
            || playerInput.equals("kertas", true) && comInput.equals("batu", true)
            || playerInput.equals("gunting", true) && comInput.equals("kertas", true)
        ) {
            Log.d("Hasil", "Pemain 1 Menang")
            layout.textWin.text = "Pemain 1\nMENANG!"
//            layout.textMenang.text = "MENANG!"
            layout.textWin.setBackgroundResource(R.drawable.backround_win)
            layout.textMenang.setBackgroundResource(R.drawable.backround_win)
        } else if (comInput.equals("batu", true) && playerInput.equals("gunting", true)
            || comInput.equals("kertas", true) && playerInput.equals("batu", true)
            || comInput.equals("gunting", true) && playerInput.equals("kertas", true)
        ) {
            Log.d("Hasil", "Pemain 2 Menang")
            layout.textWin.text = "Pemain 2\nMENANG!"
//            layout.textMenang.text = "MENANG!"
            layout.textWin.setBackgroundResource(R.drawable.backround_win)
            layout.textMenang.setBackgroundResource(R.drawable.backround_win)
        }
    }
}