package com.developercara.bcanotes.pomodoro

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Vibrator
import android.view.Window
import com.developercara.bcanotes.R
import com.developercara.bcanotes.databinding.ActivityPomodoroTimeBinding

class PomodoroTime : AppCompatActivity() {

private lateinit var binding: ActivityPomodoroTimeBinding

private var isTimerRunning = false
private var timeRemaining = 0L//25L * 60L * 1000L//25L * 60L * 1000L // 25 minutes in milliseconds
private var timeRemaining2 = 0L
private var timer: CountDownTimer? = null
private lateinit var vibrator: Vibrator


override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityPomodoroTimeBinding.inflate(layoutInflater)
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    setContentView(binding.root)
    val pomodoroTimer = intent.getLongExtra("POMODORO_TIME", 25 * 60 * 1000L)

//        val breakTimer = intent.getLongExtra("BREAK_TIME", 5 * 60 * 1000L)

    timeRemaining = pomodoroTimer


    //passing to textview
    binding.timerTextView.text = "${(timeRemaining / 1000L) / 60}:${(timeRemaining / 1000L) % 60}"



    binding.startStopButton.setOnClickListener {
        if (isTimerRunning) {
            stopTimer()
        } else {

            startTimer()
        }
    }

    binding.resetButton.setOnClickListener {
        resetTimer()
    }
    binding.settingButton.setOnClickListener{
        redirectToSettingActivity()
    }


//vibrating
    vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
//
}

private fun startTimer() {
    isTimerRunning = true
    binding.startStopButton.text = "Stop"
    binding.resetButton.isEnabled = false

    //  Vibrate for 100 milliseconds
    val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    vibrator.vibrate(500)
    //val pomodoroTimer = intent.getLongExtra("POMODORO_TIME", 55 * 60 * 1000L)
//        val breakTimer = intent.getLongExtra("BREAK_TIME", 5 * 60 * 1000L)
    // timeRemaining = pomodoroTimer

    timer = object : CountDownTimer(timeRemaining, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            timeRemaining = millisUntilFinished
            updateTimerText(millisUntilFinished)

        }


        override fun onFinish() {
            isTimerRunning = false
            binding.startStopButton.text = "Start"
            binding.resetButton.isEnabled = true
            vibrator.vibrate(50)

            // Redirect to another fragment
            redirectToNewActivity()
        }
    }.start()
}


private fun stopTimer() {
    isTimerRunning = false
    binding.startStopButton.text = "Start"
    binding.resetButton.isEnabled = true
    val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    vibrator.vibrate(100)

    timer?.cancel()
}

private fun resetTimer() {
    stopTimer()
    val pomodoroTimer = intent.getLongExtra("POMODORO_TIME", 25 * 60 * 1000L)
//        val breakTimer = intent.getLongExtra("BREAK_TIME", 5 * 60 * 1000L)

    timeRemaining = pomodoroTimer
    updateTimerText(timeRemaining)
}

private fun updateTimerText(millisUntilFinished: Long) {
    val minutes = (millisUntilFinished / 1000) / 60
    val seconds = (millisUntilFinished / 1000) % 60
    binding.timerTextView.text = "$minutes:$seconds"
    binding.timerTextView2.text = "${minutes}:${String.format("%02d", seconds)}"
}


private fun redirectToNewActivity() {
    val intent = Intent(this, BreakPromo::class.java)
    startActivity(intent)
}
private  fun redirectToSettingActivity(){
    val intent = Intent(this, Settingpromo::class.java)
    startActivity(intent)
}



}