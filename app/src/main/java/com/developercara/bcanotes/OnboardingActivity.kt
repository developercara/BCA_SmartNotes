package com.developercara.bcanotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.developercara.bcanotes.Adapter.OnboardingPagerAdapter
import com.google.android.material.tabs.TabLayout

class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        val viewPager: ViewPager = findViewById(R.id.view_pager)
        val tabLayout: TabLayout = findViewById(R.id.tab_layout)

        val adapter = OnboardingPagerAdapter(supportFragmentManager)
        adapter.addFragment(OnboardingFragment.newInstance(R.drawable.a))
        adapter.addFragment(OnboardingFragment.newInstance(R.drawable.b))
        adapter.addFragment(OnboardingFragment.newInstance(R.drawable.c))

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }
}