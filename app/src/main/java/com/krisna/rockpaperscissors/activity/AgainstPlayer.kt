package com.krisna.rockpaperscissors.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.krisna.rockpaperscissors.R
import com.krisna.rockpaperscissors.databinding.ActivityAgainstPlayerBinding
import com.krisna.rockpaperscissors.fragments.WinnerDialogFragment
import kotlin.random.Random

class AgainstPlayer : AppCompatActivity() {

    private lateinit var binding: ActivityAgainstPlayerBinding
    private lateinit var name: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgainstPlayerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val bundle = intent.extras
        name = bundle?.getString("nameUser")!!

        binding.textPemainOne.text = name

        binding.btnBatu.setOnClickListener {
            Log.d("User Input", "Batu")
            playerInput("", "Batu")
        }

        binding.btnKertas.setOnClickListener {
            Log.d("User Input", "Kertas")
            playerInput("", "Kertas")
        }

        binding.btnGunting.setOnClickListener {
            Log.d("User Input", "Gunting")
            playerInput("", "Gunting")
        }

        binding.btnRefresh.setOnClickListener {
            Log.d("User Input", "Button Refresh di Click")
            playerInput("VS", "Reset")
        }

        binding.btnCancel.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }

    private fun btnValue(hasTo: Boolean) {
        binding.btnGunting.isEnabled = hasTo
        binding.btnKertas.isEnabled = hasTo
        binding.btnBatu.isEnabled = hasTo
    }

    private fun playerInput(textVersus: String, btnPlayer: String) {

        when (btnPlayer) {
            "Batu" -> {
                binding.textVersus.text = textVersus
                binding.btnBatu.setBackgroundResource(R.drawable.roundcorner)
                checkResult("batu", getBotInput())
                btnValue(false)
            }
            "Kertas" -> {
                binding.textVersus.text = textVersus
                binding.btnKertas.setBackgroundResource(R.drawable.roundcorner)
                checkResult("kertas", getBotInput())
                btnValue(false)

            }
            "Gunting" -> {
                binding.textVersus.text = textVersus
                binding.btnGunting.setBackgroundResource(R.drawable.roundcorner)
                checkResult("gunting", getBotInput())
                btnValue(false)
            }
            "Reset" -> {
                btnValue(true)
                binding.textVersus.text = textVersus

                binding.btnBatu.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                binding.btnKertas.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                binding.btnGunting.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

                binding.btnBatuCom.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                binding.btnKertasCom.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                binding.btnGuntingCom.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
            }
        }

    }

    private fun getBotInput(): String {
        val options = arrayOf("batu", "kertas", "gunting")
        val botOptions: Int = Random.nextInt(options.size)
        when {
            options[botOptions] == "batu" -> {
                Log.d("Bot Input", "Batu")
                binding.btnBatuCom.setBackgroundResource(R.drawable.roundcorner)
                toastBot("Batu")
            }
            options[botOptions] == "kertas" -> {
                Log.d("Bot Input", "Kertas")
                binding.btnKertasCom.setBackgroundResource(R.drawable.roundcorner)
                toastBot("Kertas")
            }
            options[botOptions] == "gunting" -> {
                Log.d("Bot Input", "Gunting")
                binding.btnGuntingCom.setBackgroundResource(R.drawable.roundcorner)
                toastBot("Gunting")
            }
        }
        return options[botOptions]
    }

    private fun checkResult(playerInput: String, comInput: String) {
        if (playerInput.equals(comInput, true)) {
            Log.d("Hasil", "DRAW")
            val winnerDialogFragment = WinnerDialogFragment("SERI")
            winnerDialogFragment.show(supportFragmentManager, null)
        } else if (playerInput.equals("batu", true) && comInput.equals("gunting", true)
            || playerInput.equals("kertas", true) && comInput.equals("batu", true)
            || playerInput.equals("gunting", true) && comInput.equals("kertas", true)
        ) {
            Log.d("Hasil", "Pemain 1 Menang")
            val winnerDialogFragment = WinnerDialogFragment(name)
            winnerDialogFragment.show(supportFragmentManager, null)
        } else if (comInput.equals("batu", true) && playerInput.equals("gunting", true)
            || comInput.equals("kertas", true) && playerInput.equals("batu", true)
            || comInput.equals("gunting", true) && playerInput.equals("kertas", true)
        ) {
            Log.d("Hasil", "Pemain 2 Menang")
            val winnerDialogFragment = WinnerDialogFragment("CPU")
            winnerDialogFragment.show(supportFragmentManager, null)
        }
    }

    private fun toastBot(choosen: String){
        Toast.makeText(this, "CPU Memilih $choosen", Toast.LENGTH_SHORT).show()
    }
}