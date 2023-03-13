package com.developercara.bcanotes.sem1subject.mathematics1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.developercara.bcanotes.R
import com.developercara.bcanotes.databinding.ActivityMathematicsSyllabusBinding
import com.github.barteksc.pdfviewer.PDFView
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class mathematicsSyllabus : AppCompatActivity() {
    lateinit var binding: ActivityMathematicsSyllabusBinding
    private lateinit var pdfView: PDFView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMathematicsSyllabusBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.spinKit
        // Setup PDFView
        pdfView = binding.pdfView

        // Get a reference to the Firebase Storage instance
        val storageRef = Firebase.storage.reference
        // Create a reference to the PDF file you want to download
        val pdfRef = storageRef.child("/semester1/math/Syllabus.pdf")
        showProgressBar()
        // Download the PDF file to a byte array
        pdfRef.getBytes(10 * 1024 * 1024)
            .addOnSuccessListener { bytes ->
                // Convert the byte array to a PDF document and display it in the PDFView
                pdfView.fromBytes(bytes)
                    .enableSwipe(true)
                    .swipeHorizontal(false)
                    .load()
                hideProgressBar()
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, "Error downloading PDF", exception)
                hideProgressBar()
            }
    }

    companion object {
        private const val TAG = "MathematicActivity"
    }
    private fun showProgressBar() {
        binding.spinKit.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.spinKit.visibility = View.GONE

    }

}