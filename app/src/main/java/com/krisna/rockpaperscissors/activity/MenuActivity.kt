package com.krisna.rockpaperscissors.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.krisna.rockpaperscissors.UserData
import com.krisna.rockpaperscissors.databinding.ActivityMenuBinding
import java.util.*

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sharedPreferences = getSharedPreferences("prefRPS", 0)

        val username = sharedPreferences.getString("userName", "")

        val str = capitalizeFirst(username)

        binding.titleMenuFirst.text = "$str vs Pemain"
        binding.titleMenuSecond.text = "$str vs CPU"

        Snackbar.make(binding.root,"Selamat datang $str", Snackbar.LENGTH_INDEFINITE)
            .setAction("Tutup"){

            }.show()

        binding.imageMenuFirst.setOnClickListener {
            vsOtherPlayer(username)
        }

        binding.imageMenuSecond.setOnClickListener {
            vsCpu(str)
        }
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

    private fun vsCpu(name: String?){
        val intent = Intent(this, MainActivity::class.java)
        val bundle = Bundle()
        val user = UserData()
        user.userName = name
        bundle.putParcelable("nameUser" , user)
        intent.putExtras(bundle)
        startActivity(intent)

    }
    private fun vsOtherPlayer(name: String?){
        val intent = Intent(this, AgainstPlayer::class.java)
        val bundle = Bundle()
        val user = UserData()
        user.userName = name
        bundle.putParcelable("nameUser" , user)
        intent.putExtras(bundle)
        startActivity(intent)

    }
}



