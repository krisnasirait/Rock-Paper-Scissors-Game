package com.krisna.rockpaperscissors.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.krisna.rockpaperscissors.R
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
}