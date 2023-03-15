package com.developercara.bcanotes.practicalnotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.developercara.bcanotes.R


class displayCode : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_code)

        val text = findViewById<TextView>(R.id.codingContentTextView)
        val codingText = intent.getStringExtra("code") ?: ""
        text.text = codingText



    }
}