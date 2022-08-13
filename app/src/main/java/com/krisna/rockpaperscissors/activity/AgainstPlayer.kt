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

        checkResult(playerOneListener(), playerTwoListener())

        binding.btnRefresh.setOnClickListener {
            Log.d("User Input", "Button Refresh di Click")

            binding.btnBatu.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
            binding.btnKertas.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
            binding.btnGunting.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

            binding.btnBatuSecond.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
            binding.btnBatuSecond.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
            binding.btnBatuSecond.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
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

    private fun playerOneListener() : String {
        lateinit var pOneInput: String

        binding.btnBatu.setOnClickListener {
            Log.d("User 1 Input", "Batu")
            binding.btnBatu.setBackgroundResource(R.drawable.roundcorner)
            btnValue(false)
            pOneInput = "Batu"
        }

        binding.btnKertas.setOnClickListener {
            Log.d("User 1 Input", "Kertas")
            binding.btnBatu.setBackgroundResource(R.drawable.roundcorner)
            btnValue(false)
            pOneInput = "Kertas"
        }

        binding.btnGunting.setOnClickListener {
            Log.d("User 1 Input", "Gunting")
            binding.btnBatu.setBackgroundResource(R.drawable.roundcorner)
            btnValue(false)
            pOneInput = "Gunting"
        }

        return pOneInput
    }

    private fun playerTwoListener() : String {
        lateinit var pTwoInput: String

        binding.btnBatuSecond.setOnClickListener {
            Log.d("User 1 Input", "Batu")
            binding.btnBatu.setBackgroundResource(R.drawable.roundcorner)
            btnValue(false)
            pTwoInput = "Batu"
        }

        binding.btnKertasSecond.setOnClickListener {
            Log.d("User 1 Input", "Kertas")
            binding.btnBatu.setBackgroundResource(R.drawable.roundcorner)
            btnValue(false)
            pTwoInput = "Kertas"
        }

        binding.btnGuntingSecond.setOnClickListener {
            Log.d("User 1 Input", "Gunting")
            binding.btnBatu.setBackgroundResource(R.drawable.roundcorner)
            btnValue(false)
            pTwoInput = "Gunting"
        }

        return pTwoInput
    }

    private fun checkResult(playerInput: String, secondPlayerInput: String) {
        if (playerInput.equals(secondPlayerInput, true)) {
            Log.d("Hasil", "DRAW")
            val winnerDialogFragment = WinnerDialogFragment("SERI")
            winnerDialogFragment.show(supportFragmentManager, null)
        } else if (playerInput.equals("batu", true) && secondPlayerInput.equals("gunting", true)
            || playerInput.equals("kertas", true) && secondPlayerInput.equals("batu", true)
            || playerInput.equals("gunting", true) && secondPlayerInput.equals("kertas", true)
        ) {
            Log.d("Hasil", "Pemain 1 Menang")
            val winnerDialogFragment = WinnerDialogFragment(name)
            winnerDialogFragment.show(supportFragmentManager, null)
        } else if (secondPlayerInput.equals("batu", true) && playerInput.equals("gunting", true)
            || secondPlayerInput.equals("kertas", true) && playerInput.equals("batu", true)
            || secondPlayerInput.equals("gunting", true) && playerInput.equals("kertas", true)
        ) {
            Log.d("Hasil", "Pemain 2 Menang")
            val winnerDialogFragment = WinnerDialogFragment("Pemain 2")
            winnerDialogFragment.show(supportFragmentManager, null)
        }
    }

    private fun toastBot(choosen: String){
        Toast.makeText(this, "CPU Memilih $choosen", Toast.LENGTH_SHORT).show()
    }
}