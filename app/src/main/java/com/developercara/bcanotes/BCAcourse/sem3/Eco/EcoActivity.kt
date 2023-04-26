package com.developercara.bcanotes.BCAcourse.sem2.ECO

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developercara.bcanotes.BCAcourse.sem2.EVS.EVSAdapter
import com.developercara.bcanotes.R
import com.developercara.bcanotes.databinding.ActivityEcoBinding
import com.developercara.bcanotes.dataclass.Others

class EcoActivity : AppCompatActivity() {
    lateinit var binding: ActivityEcoBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EcoAdapter
    private val cList = mutableListOf<Others>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEcoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        recyclerView = binding.recyclerc
        adapter = EcoAdapter(this, cList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        prepareSemesListData()
    }

    private fun prepareSemesListData() {
        cList.add(Others("SYLLABUS", R.drawable.a))
        cList.add(Others("Unit I", R.drawable.b))
        cList.add(Others("Unit II", R.drawable.b))
        cList.add(Others("Unit III", R.drawable.d))
        cList.add(Others("Unit IV", R.drawable.d))
        cList.add(Others("Unit V", R.drawable.d))
        cList.add(Others("Unit V", R.drawable.d))
        adapter.notifyDataSetChanged()
    }
}