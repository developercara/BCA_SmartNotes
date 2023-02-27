package com.developercara.bcanotes.sem1subject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.developercara.bcanotes.R
import com.developercara.bcanotes.databinding.ActivityMathematicBinding
import com.github.barteksc.pdfviewer.PDFView
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class MathematicActivity : AppCompatActivity() {
    lateinit var binding: ActivityMathematicBinding
    private lateinit var pdfView: PDFView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMathematicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup PDFView
        pdfView = binding.pdfView

        // Get a reference to the Firebase Storage instance
        val storageRef = Firebase.storage.reference
        // Create a reference to the PDF file you want to download
        val pdfRef = storageRef.child("/semester1/math/sample.pdf")

        // Download the PDF file to a byte array
        pdfRef.getBytes(10 * 1024 * 1024)
            .addOnSuccessListener { bytes ->
                // Convert the byte array to a PDF document and display it in the PDFView
                pdfView.fromBytes(bytes)
                    .enableSwipe(true)
                    .swipeHorizontal(false)
                    .load()
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, "Error downloading PDF", exception)
            }
    }

    companion object {
        private const val TAG = "MathematicActivity"
    }

}
