package com.developercara.bcanotes.inappPurchase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.developercara.bcanotes.databinding.ActivityGetQuestionBinding
import com.developercara.bcanotes.dataclass.Semes

class getQuestionActivity : AppCompatActivity() {
    private var recyclerView : RecyclerView?=null
    private var SemesAdapter: getQuestionAdapter?=null
    private var semesList = mutableListOf<Semes>()
    lateinit var binding: ActivityGetQuestionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}