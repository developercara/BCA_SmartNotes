package com.developercara.bcanotes.quizgame

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.developercara.bcanotes.R
import com.mikhaellopez.circularprogressbar.CircularProgressBar

class WonActivity : AppCompatActivity() {

    private lateinit var resultText: TextView
    private var correct = 0
    private var wrong = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_won)
        val correct = intent.getIntExtra("score", 0)
        val wrong = intent.getIntExtra("score", 0)
        val scoreTextView: TextView = findViewById(R.id.progress_text)
        val progressBar: ProgressBar = findViewById(R.id.progress_bar)
        progressBar.progress=correct
        scoreTextView.text = "$correct/20"
    }
}