package com.developercara.bcanotes

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.developercara.bcanotes.Adapter.OnboardingPagerAdapter
import com.developercara.bcanotes.databinding.ActivityOnboardingBinding
import com.google.android.material.tabs.TabLayout

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var dots: Array<TextView?>
    private lateinit var viewPagerAdapter: OnboardingPagerAdapter
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean("hasSeenOnboarding", false)) {
            // User has already seen the onboarding screen, so skip it
            val i = Intent(this@OnboardingActivity, LoginActivity::class.java)
            startActivity(i)
            finish()
            return
        }

        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backbtn.setOnClickListener {
            if (getitem(0) > 0) {
                binding.slideViewPager.setCurrentItem(getitem(-1), true)
            }
        }

        binding.nextbtn.setOnClickListener {
            if (getitem(0) < 3) {
                binding.slideViewPager.setCurrentItem(getitem(1), true)
            } else {
                sharedPreferences.edit().putBoolean("hasSeenOnboarding", true).apply()
                val i = Intent(this@OnboardingActivity, LoginActivity::class.java)
                startActivity(i)
                finish()
            }
        }

        binding.skipButton.setOnClickListener {
            sharedPreferences.edit().putBoolean("hasSeenOnboarding", true).apply()
            val i = Intent(this@OnboardingActivity, LoginActivity::class.java)
            startActivity(i)
            finish()
        }

        viewPagerAdapter = OnboardingPagerAdapter(this)

        binding.slideViewPager.adapter = viewPagerAdapter

        setUpindicator(0)
        binding.slideViewPager.addOnPageChangeListener(viewListener)
    }

    private fun setUpindicator(position: Int) {
        dots = arrayOfNulls(4)
        binding.indicatorLayout.removeAllViews()

        for (i in dots.indices) {
            dots[i] = TextView(this)
            dots[i]!!.text = Html.fromHtml("&#8226").toString()
            dots[i]!!.textSize = 35f
            dots[i]!!.setTextColor(ContextCompat.getColor(applicationContext, R.color.inactive))
            binding.indicatorLayout.addView(dots[i])
        }

        dots[position]!!.setTextColor(ContextCompat.getColor(applicationContext, R.color.active))
    }

    private val viewListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

        override fun onPageSelected(position: Int) {
            setUpindicator(position)

            if (position > 0) {
                binding.backbtn.visibility = View.VISIBLE
            } else {
                binding.backbtn.visibility = View.INVISIBLE
            }
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }

    private fun getitem(i: Int): Int {
        return binding.slideViewPager.currentItem + i
    }
}