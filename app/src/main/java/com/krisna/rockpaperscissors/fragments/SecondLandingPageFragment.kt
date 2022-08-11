package com.krisna.rockpaperscissors.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.krisna.rockpaperscissors.R
import com.krisna.rockpaperscissors.databinding.FragmentSecondLandingPageBinding

class SecondLandingPageFragment : Fragment(R.layout.fragment_second_landing_page) {

    private var fragmentSecondBinding: FragmentSecondLandingPageBinding? = null
    private val binding get() = fragmentSecondBinding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentSecondBinding = FragmentSecondLandingPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivArrow.visibility = View.VISIBLE
    }

}