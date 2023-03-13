package com.developercara.bcanotes.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.developercara.bcanotes.LoginActivity
import com.developercara.bcanotes.R
import com.developercara.bcanotes.quizgame.Modelclass
import com.google.firebase.database.DatabaseReference

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var listofQ: ArrayList<Modelclass>
    private lateinit var myRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Delay for 3 seconds and then start the MainActivity
        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 3000)


        //
    }
}