package com.krisna.rockpaperscissors.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.krisna.rockpaperscissors.adapter.LandingPageAdapter
import com.krisna.rockpaperscissors.databinding.ActivityLandingBinding
import com.krisna.rockpaperscissors.fragments.FirstLandingPageFragment
import com.krisna.rockpaperscissors.fragments.SecondLandingPageFragment
import com.krisna.rockpaperscissors.fragments.ThirdLandingPageFragment
import kotlin.properties.Delegates

class LandingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLandingBinding
    private var currentLandingPagesIndex by Delegates.notNull<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        currentLandingPagesIndex = savedInstanceState?.getInt(KEY_PAGE_INDEX) ?: 0
        setupLandingCarousel()

    }

    private fun setupLandingCarousel() {
        val landingPageAdapter = LandingPageAdapter(this, getLandingPages())
        binding.vpLanding.apply {
            adapter = landingPageAdapter
            registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    binding.ivArrow.isVisible = position != 0
                    if (position == getLandingPages().size - 1) {
                        //TODO add action on scroll from last landing page if need
                    }
                    currentLandingPagesIndex = position
                }
            })
        }
        binding.tlPageIndicator.setViewPager(binding.vpLanding)
    }

    private fun getLandingPages() = listOf(
        FirstLandingPageFragment.newInstance(),
        SecondLandingPageFragment.newInstance(),
        ThirdLandingPageFragment.newInstance(),
    )

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt(KEY_PAGE_INDEX, currentLandingPagesIndex)
    }

    companion object {
        private const val KEY_PAGE_INDEX = "key_page_index"
    }
}