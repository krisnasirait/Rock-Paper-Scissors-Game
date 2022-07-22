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
            layout.textVersus.text = ""
            layout.btnBatu.setBackgroundResource(R.drawable.roundcorner)
            checkResult("batu", getBotInput())
            btnDeactivated()
        }

        layout.btnKertas.setOnClickListener {
            Log.d("User Input", "Kertas")
            layout.textVersus.text = ""
            layout.btnKertas.setBackgroundResource(R.drawable.roundcorner)
            checkResult("kertas", getBotInput())
            btnDeactivated()
        }

        layout.btnGunting.setOnClickListener {
            Log.d("User Input", "Gunting")
            layout.textVersus.text = ""
            layout.btnGunting.setBackgroundResource(R.drawable.roundcorner)
            checkResult("gunting", getBotInput())
            btnDeactivated()
        }

        layout.btnRefresh.setOnClickListener {
            Log.d("User Input", "Button Refresh di Click")
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
            layout.layoutWinner.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
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
            layout.layoutWinner.setBackgroundColor(ContextCompat.getColor(this, R.color.blue))
        } else if (playerInput.equals("batu", true) && comInput.equals("gunting", true)
            || playerInput.equals("kertas", true) && comInput.equals("batu", true)
            || playerInput.equals("gunting", true) && comInput.equals("kertas", true)
        ) {
            Log.d("Hasil", "Pemain 1 Menang")
            layout.textWin.text = "Pemain 1"
            layout.textMenang.text = "MENANG!"
            layout.layoutWinner.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.light_green
                )
            )
        } else if (comInput.equals("batu", true) && playerInput.equals("gunting", true)
            || comInput.equals("kertas", true) && playerInput.equals("batu", true)
            || comInput.equals("gunting", true) && playerInput.equals("kertas", true)
        ) {
            Log.d("Hasil", "Pemain 2 Menang")
            layout.textWin.text = "Pemain 2"
            layout.textMenang.text = "MENANG!"
            layout.layoutWinner.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.light_green
                )
            )
        }
    }
}