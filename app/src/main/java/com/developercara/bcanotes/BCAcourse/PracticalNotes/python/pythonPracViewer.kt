package com.developercara.bcanotes.BCAcourse.PracticalNotes.python

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.developercara.bcanotes.databinding.ActivityCprogrammingUnitBinding
import com.developercara.bcanotes.databinding.ActivityPracticalViewBinding
import com.github.barteksc.pdfviewer.PDFView
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class pythonPracViewer : AppCompatActivity() {
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
            0 -> storageRef.child("/practical/Cpractical/C PRATICAL NOTES.txt")
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
