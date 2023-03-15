package com.developercara.bcanotes.quizgame

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.developercara.bcanotes.MainActivity
import com.developercara.bcanotes.R
import com.developercara.bcanotes.databinding.ActivityWonBinding
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

class WonActivity : AppCompatActivity() {
lateinit var binding: ActivityWonBinding
    private lateinit var resultText: TextView
    private var correct = 0
    private var wrong = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityWonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val correct = intent.getIntExtra("score", 0)
        val wrong = intent.getIntExtra("score", 0)
        val scoreTextView: TextView = findViewById(R.id.progress_text)
        val progressBar: ProgressBar = findViewById(R.id.progress_bar)
        progressBar.progress=correct
        scoreTextView.text = "$correct/20"

        ani()
        binding.playagain.setOnClickListener {
            val intent = Intent(this,MainQuiz::class.java)
            startActivity(intent)
        }
    }
    private fun ani(){
        val party =Party(
            speed = 0f,
            maxSpeed = 30f,
            damping = 0.9f,
            spread = 360,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100),
            position = Position.Relative(0.5, 0.3)
        )
        binding.konfettiView.start(party)

    }
    override fun onBackPressed() {
        // Create an alert dialog to confirm if the user wants to exit the quiz
        AlertDialog.Builder(this)
            .setTitle("Exit quiz?")
            .setMessage("Are you sure you want to exit the quiz?")
            .setPositiveButton("Yes") { _, _ ->
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            .setNegativeButton("No", null)
            .show()
    }

}