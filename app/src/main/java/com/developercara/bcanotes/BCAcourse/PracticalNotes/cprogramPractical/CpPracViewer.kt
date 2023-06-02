package com.developercara.bcanotes.BCAcourse.PracticalNotes.cprogramPractical


import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi

import com.developercara.bcanotes.databinding.ActivityPracticalViewBinding

import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class CpPracViewer : AppCompatActivity() {
    lateinit var binding: ActivityPracticalViewBinding
    private lateinit var textView: TextView

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticalViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textView = binding.textView


        val unitNumber = intent.getIntExtra("unit_number", 0)

        // Get a reference to the Firebase Storage instance
        val storageRef = Firebase.storage.reference

        // Create a reference to the text file you want to download
        val textRef = when (unitNumber) {
            0 -> storageRef.child("/practical/Cpractical/1.WRITE A C PROGRAM TO FIND SIMPLE .txt")
            1 -> storageRef.child("/practical/Cpractical/2.WRITE A C PROGRAM FIND HIGHEST OF THREE NUMBER.txt")
            2 -> storageRef.child("/practical/Cpractical/3.WRITE A C PROGRAM TO PRINT RIGHT .txt")
            3 -> storageRef.child("/practical/Cpractical/4.WRITE A C PROGRAM TO PRINT REVERSE RIGHT TRIANGLE.txt")
            4 -> storageRef.child("/practical/Cpractical/5.WRITE A C PROGRAM TO PRINT STAR P.txt")
            5 -> storageRef.child("/practical/Cpractical/6.WRITE A C PROGRAM TO CREATE MENU .txt")
            6 -> storageRef.child("/practical/Cpractical/7.WRITE AC PROGRAM TO FIND A GRADE .txt")
            7 -> storageRef.child("/practical/Cpractical/8.WRITE A C PROGRAM TO SWAP TWO NUM.txt")
            8 -> storageRef.child("/practical/Cpractical/9.WRITE A C PROGRAM TO CREATE  A CA.txt")
            9 -> storageRef.child("/practical/Cpractical/10.WRITE A C PROGRAM TO CALCULATE G.txt")
            10 -> storageRef.child("/practical/Cpractical/11.WRITE A C PROGRAM FOR  FIBONACCI.txt")
            11 -> storageRef.child("/practical/Cpractical/12.WRITE A C PROGRAM TO PRINT PRIME.txt")
            12 -> storageRef.child("/practical/Cpractical/13.WRITE A CPROGRAM THAT REVERSE A .txt")
            13 -> storageRef.child("/practical/Cpractical/14.WRITE A C PROGRAM TO CREATE A SQ.txt")
            14 -> storageRef.child("/practical/Cpractical/15.WRITE A C PROGRAM TO PRINT HALLOW SQUARE STAR PATTERN.txt")
            15 -> storageRef.child("/practical/Cpractical/16.WRITE A C PROGRAM TO CHECK ARMSTRONG NUMBER.txt")
            else -> null
        }

        if (textRef != null) {
            showProgressBar()

            // Download the text file to a byte array
            textRef.getBytes(10 * 1024 * 1024)
                .addOnSuccessListener { bytes ->
                    // Convert the byte array to a String and display it in the TextView
                    val text = String(bytes, Charsets.UTF_8)
                    textView.text = text
                    hideProgressBar()
                }
                .addOnFailureListener { exception ->
                    Log.e(TAG, "Error downloading text file", exception)
                    hideProgressBar()
                }
        }
    }

    companion object {
        private const val TAG = "CpPracViewer"
    }

    private fun showProgressBar() {
        binding.spinKit.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.spinKit.visibility = View.GONE
    }
}

