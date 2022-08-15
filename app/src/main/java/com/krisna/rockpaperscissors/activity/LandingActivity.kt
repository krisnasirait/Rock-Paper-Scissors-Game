package com.krisna.rockpaperscissors.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.krisna.rockpaperscissors.adapter.LandingPageAdapter
import com.krisna.rockpaperscissors.databinding.ActivityLandingBinding
import com.krisna.rockpaperscissors.fragments.FirstLandingPageFragment
import com.krisna.rockpaperscissors.fragments.SecondLandingPageFragment
import com.krisna.rockpaperscissors.fragments.ThirdLandingPageFragment

class LandingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val pagerAdapter = LandingPageAdapter(
            fragmentActivity = this,
        )

        pagerAdapter.setFragments(
            listOf(
                FirstLandingPageFragment(),
                SecondLandingPageFragment(),
                ThirdLandingPageFragment()
            )
        )

        binding.vpLanding.adapter = pagerAdapter
        binding.tlPageIndicator.setViewPager(binding.vpLanding)
    }
}