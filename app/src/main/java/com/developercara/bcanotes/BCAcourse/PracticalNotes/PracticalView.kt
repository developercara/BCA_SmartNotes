package com.developercara.bcanotes.BCAcourse.PracticalNotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.developercara.bcanotes.R
import com.developercara.bcanotes.databinding.ActivityPracticalViewBinding
import com.github.barteksc.pdfviewer.PDFView

class PracticalView : AppCompatActivity() {
    lateinit var binding: ActivityPracticalViewBinding
    private lateinit var pdfView: PDFView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticalViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}