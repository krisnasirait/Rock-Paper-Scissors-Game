package com.krisna.rockpaperscissors.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.krisna.rockpaperscissors.R
import com.krisna.rockpaperscissors.activity.MenuActivity
import com.krisna.rockpaperscissors.databinding.FragmentThirdLandingPageBinding

class ThirdLandingPageFragment : Fragment(R.layout.fragment_third_landing_page) {

    private var fragmentThirdBinding: FragmentThirdLandingPageBinding? = null
    private val binding get() = fragmentThirdBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentThirdBinding = FragmentThirdLandingPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.ivArrow.visibility = View.VISIBLE

        binding.ivArrow.setOnClickListener {
            activity.let {

                val name: String = binding.etLanding.text.toString()
                Log.d("nameInFragment", "$name")

                val intent = Intent(it, MenuActivity::class.java)
                intent.putExtra("userName", name)
                startActivity(intent)
            }

        }
    }
}