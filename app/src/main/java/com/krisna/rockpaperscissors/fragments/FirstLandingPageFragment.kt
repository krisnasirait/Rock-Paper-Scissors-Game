package com.krisna.rockpaperscissors.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.krisna.rockpaperscissors.R
import com.krisna.rockpaperscissors.databinding.FragmentFirstLandingPageBinding

class FirstLandingPageFragment : Fragment(R.layout.fragment_first_landing_page) {

    private var fragmentFirstBinding: FragmentFirstLandingPageBinding? = null
    private val binding get() = fragmentFirstBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentFirstBinding = FragmentFirstLandingPageBinding.inflate(inflater, container, false)
        return binding.root
    }
}