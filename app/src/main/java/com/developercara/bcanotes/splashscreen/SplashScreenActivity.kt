package com.developercara.bcanotes.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

import com.developercara.bcanotes.OnboardingActivity
import com.developercara.bcanotes.R


@Suppress("DEPRECATION")
@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Delay for 3 seconds and then start the MainActivity
        Handler().postDelayed({
            startActivity(Intent(this, OnboardingActivity::class.java))
            finish()
        }, 3000)


    }
}