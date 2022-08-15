package com.krisna.rockpaperscissors.activity

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.krisna.rockpaperscissors.CallBackResultInteraction
import com.krisna.rockpaperscissors.R
import com.krisna.rockpaperscissors.UserData
import com.krisna.rockpaperscissors.databinding.ActivityAgainstPlayerBinding
import com.krisna.rockpaperscissors.fragments.WinnerDialogFragment
import java.util.*

class AgainstPlayer : AppCompatActivity(), CallBackResultInteraction {


    private lateinit var binding: ActivityAgainstPlayerBinding
    private lateinit var name: String
    private var isPlayer1Turn= true
    private var player1Input =""
    private var player2Input =""
    override fun finishGame() {
        finish()
    }

    override fun resetGame() {
        playerInput("VS", "Reset")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgainstPlayerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val bundle = intent.extras
        val userData = bundle?.getParcelable<UserData>("nameUser")!!
        name = capitalizeFirst(userData.userName) ?: ""

        binding.textPemainOne.text = name
        playerInput("VS", "Reset")
        binding.btnBatu.setOnClickListener {
            playerInput("", "Batu", binding.btnBatu)
        }

        binding.btnKertas.setOnClickListener {
            playerInput("", "Kertas", binding.btnKertas)
        }

        binding.btnGunting.setOnClickListener {
            playerInput("", "Gunting", binding.btnGunting)
        }
        binding.btnBatuCom.setOnClickListener {
            playerInput("", "Batu", binding.btnBatuCom)
        }

        binding.btnKertasCom.setOnClickListener {
            playerInput("", "Kertas", binding.btnKertasCom)
        }

        binding.btnGuntingCom.setOnClickListener {
            playerInput("", "Gunting", binding.btnGuntingCom)
        }

        binding.btnRefresh.setOnClickListener {
            playerInput("VS", "Reset")
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun btnValuePlayer1(hasTo: Boolean) {
        binding.btnGunting.isEnabled = hasTo
        binding.btnKertas.isEnabled = hasTo
        binding.btnBatu.isEnabled = hasTo
    }
    private fun btnValuePlayer2(hasTo: Boolean) {
        binding.btnGuntingCom.isEnabled = hasTo
        binding.btnKertasCom.isEnabled = hasTo
        binding.btnBatuCom.isEnabled = hasTo
    }

    private fun playerInput(textVersus: String, btnPlayer: String, btn : ImageButton?= null) {
        if(isPlayer1Turn){
            player1Input=btnPlayer
            isPlayer1Turn= false
            btnValuePlayer1(false)
            btnValuePlayer2(true)
        }else{
            binding.textVersus.text = textVersus
            player2Input=btnPlayer
            checkResult(player1Input, player2Input)
            if(btnPlayer != "Reset"){
                toastBot(btnPlayer)
            }
        }

        when (btnPlayer) {
            "Batu" -> {
                btn?.setBackgroundResource(R.drawable.roundcorner)
            }
            "Kertas" -> {
                btn?.setBackgroundResource(R.drawable.roundcorner)
            }
            "Gunting" -> {
                btn?.setBackgroundResource(R.drawable.roundcorner)
            }
            "Reset" -> {
                isPlayer1Turn=true
                btnValuePlayer1(true)
                btnValuePlayer2(false)
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
            val winnerDialogFragment = WinnerDialogFragment("Pemain 2", this)
            winnerDialogFragment.show(supportFragmentManager, null)
        }
    }

    private fun toastBot(choosen: String){
        Toast.makeText(this, "Pemain 2 Memilih $choosen", Toast.LENGTH_SHORT).show()
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