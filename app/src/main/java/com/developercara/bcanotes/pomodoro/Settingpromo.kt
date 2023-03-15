package com.developercara.bcanotes.pomodoro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import com.developercara.bcanotes.R
import com.developercara.bcanotes.databinding.ActivitySettingpromoBinding

class Settingpromo : AppCompatActivity() {
    private lateinit var binding: ActivitySettingpromoBinding
    private lateinit var pomodoroSeekbar: SeekBar
    private lateinit var breakSeekbar: SeekBar
    private lateinit var pomodoroTimerTitle: TextView
    private lateinit var breakTimerTitle: TextView
    private var pomodoroTime: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingpromoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pomodoroSeekbar = binding.pomodoroTimerSeekbar
        breakSeekbar = binding.breakTimerSeekbar
        pomodoroTimerTitle = binding.pomodoroTimerTitle
        breakTimerTitle = binding.breakTimerTitle


        val saveButton = binding.saveButton
        saveButton.setOnClickListener {

            val pomodoroTime = pomodoroSeekbar.progress * 60 * 1000L
            val breakTime = breakSeekbar.progress * 60 * 1000L


            val intent = Intent(this, PomodoroTime::class.java)
            intent.putExtra("POMODORO_TIME", pomodoroTime)
            startActivity(intent)

        }



        pomodoroSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                pomodoroTimerTitle.text = "Custom Pomodoro Timer: $progress minutes"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
        breakSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                breakTimerTitle.text = "Custom Pomodoro Timer: $progress minutes"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }


}