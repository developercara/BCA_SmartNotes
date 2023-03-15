package com.developercara.bcanotes.pomodoro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.developercara.bcanotes.databinding.ActivityBreakPromoBinding

class BreakPromo : AppCompatActivity() {
    private lateinit var binding: ActivityBreakPromoBinding
    private var isTimerRunning = false
    private var timeRemaining = 5L * 60L * 1000L // 5 minutes in milliseconds
    private var timer: CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBreakPromoBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.relaxSkipButton.setOnClickListener {
            if (!isTimerRunning) {
                Toast.makeText(this, "Take a break and relax!", Toast.LENGTH_SHORT).show()
                startTimer()
            } else {
                stopTimer()
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("Skip")
                    .setMessage("Are you sure you want to skip?")
                    .setPositiveButton("Yes") { _, _ ->
                        redirectToNewActivity()
                    }
                    .setNegativeButton("No") { dialog, _ ->
                        dialog.cancel()
                    }
                    .create()
                    .show()
            }
            // redirect to another activity

        }
    }


    private fun startTimer() {
        isTimerRunning = true
        binding.relaxSkipButton.text = "Skip"

        timer = object : CountDownTimer(timeRemaining, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeRemaining = millisUntilFinished
                updateTimerText(millisUntilFinished)
            }

            override fun onFinish() {
                isTimerRunning = false
                binding.relaxSkipButton.text = "Relax"
            }
        }.start()
    }

    private fun stopTimer() {
        isTimerRunning = false
        binding.relaxSkipButton.text = "Relax"

        timer?.cancel()
    }

    private fun updateTimerText(millisUntilFinished: Long) {
        val minutes = (millisUntilFinished / 1000) / 60
        val seconds = (millisUntilFinished / 1000) % 60
        binding.timerTextView.text = "$minutes:$seconds"
    }
    private fun redirectToNewActivity() {
        val intent = Intent(this, PomodoroTime::class.java)
        startActivity(intent)
    }
}