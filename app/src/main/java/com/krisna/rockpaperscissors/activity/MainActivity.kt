package com.krisna.rockpaperscissors.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.krisna.rockpaperscissors.CallBackResultInteraction
import com.krisna.rockpaperscissors.R
import com.krisna.rockpaperscissors.UserData
import com.krisna.rockpaperscissors.databinding.ActivityMainBinding
import com.krisna.rockpaperscissors.fragments.WinnerDialogFragment
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(), CallBackResultInteraction {

    private lateinit var layout: ActivityMainBinding
    private lateinit var name: String
    override fun finishGame() {
        finish()
    }

    override fun resetGame() {
        playerInput("VS", "Reset")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layout = ActivityMainBinding.inflate(layoutInflater)
        val view = layout.root
        setContentView(view)

        val bundle = intent.extras
        val userData = bundle?.getParcelable<UserData>("nameUser")!!
        name = capitalizeFirst(userData.userName) ?: ""

        layout.textPemainOne.text = name

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
            playerInput("VS", "Reset")
        }

        layout.btnCancel.setOnClickListener {
            finish()
//            val intent = Intent(this, MenuActivity::class.java)
//            startActivity(intent)
        }
    }

    private fun btnValue(hasTo: Boolean) {
        layout.btnGunting.isEnabled = hasTo
        layout.btnKertas.isEnabled = hasTo
        layout.btnBatu.isEnabled = hasTo
    }

    private fun playerInput(textVersus: String, btnPlayer: String) {

        when (btnPlayer) {
            "Batu" -> {
                layout.textVersus.text = textVersus
                layout.btnBatu.setBackgroundResource(R.drawable.roundcorner)
                checkResult("batu", getBotInput())
                btnValue(false)
            }
            "Kertas" -> {
                layout.textVersus.text = textVersus
                layout.btnKertas.setBackgroundResource(R.drawable.roundcorner)
                checkResult("kertas", getBotInput())
                btnValue(false)

            }
            "Gunting" -> {
                layout.textVersus.text = textVersus
                layout.btnGunting.setBackgroundResource(R.drawable.roundcorner)
                checkResult("gunting", getBotInput())
                btnValue(false)
            }
            "Reset" -> {
                btnValue(true)
                layout.textVersus.text = textVersus

                layout.btnBatu.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                layout.btnKertas.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                layout.btnGunting.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

                layout.btnBatuCom.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                layout.btnKertasCom.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                layout.btnGuntingCom.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
            }
        }

    }

    private fun getBotInput(): String {
        val options = arrayOf("batu", "kertas", "gunting")
        val botOptions: Int = Random.nextInt(options.size)
        when {
            options[botOptions] == "batu" -> {
                Log.d("Bot Input", "Batu")
                layout.btnBatuCom.setBackgroundResource(R.drawable.roundcorner)
                toastBot("Batu")
            }
            options[botOptions] == "kertas" -> {
                Log.d("Bot Input", "Kertas")
                layout.btnKertasCom.setBackgroundResource(R.drawable.roundcorner)
                toastBot("Kertas")
            }
            options[botOptions] == "gunting" -> {
                Log.d("Bot Input", "Gunting")
                layout.btnGuntingCom.setBackgroundResource(R.drawable.roundcorner)
                toastBot("Gunting")
            }
        }
        return options[botOptions]
    }

    private fun checkResult(playerInput: String, comInput: String) {
        if (playerInput.equals(comInput, true)) {
            Log.d("Hasil", "DRAW")
            val winnerDialogFragment = WinnerDialogFragment("SERI", this)
            winnerDialogFragment.show(supportFragmentManager, null)
        } else if (playerInput.equals("batu", true) && comInput.equals("gunting", true)
            || playerInput.equals("kertas", true) && comInput.equals("batu", true)
            || playerInput.equals("gunting", true) && comInput.equals("kertas", true)
        ) {
            Log.d("Hasil", "Pemain 1 Menang")
            val winnerDialogFragment = WinnerDialogFragment(name, this)
            winnerDialogFragment.show(supportFragmentManager, null)
        } else if (comInput.equals("batu", true) && playerInput.equals("gunting", true)
            || comInput.equals("kertas", true) && playerInput.equals("batu", true)
            || comInput.equals("gunting", true) && playerInput.equals("kertas", true)
        ) {
            Log.d("Hasil", "Pemain 2 Menang")
            val winnerDialogFragment = WinnerDialogFragment("CPU", this)
            winnerDialogFragment.show(supportFragmentManager, null)
        }
    }

    private fun toastBot(choosen: String) {
        Toast.makeText(this, "CPU Memilih $choosen", Toast.LENGTH_SHORT).show()
    }

    private fun capitalizeFirst(name: String?): String? {
        val str = name?.split(" ")
            ?.joinToString(separator = " ") { it ->
                it.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                }
            }

        return str
    }
}