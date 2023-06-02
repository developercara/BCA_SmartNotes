package com.developercara.bcanotes.BCAcourse.PracticalNotes.python

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developercara.bcanotes.BCAcourse.PracticalNotes.cprogramPractical.CpPratical
import com.developercara.bcanotes.R
import com.developercara.bcanotes.databinding.ActivityPythonPracBinding
import com.developercara.bcanotes.dataclass.Others

class pythonPracActivity : AppCompatActivity() {
    lateinit var binding: ActivityPythonPracBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: pythonPracAdapter
    private val cList = mutableListOf<Others>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPythonPracBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        recyclerView = binding.recyclerc
        adapter = pythonPracAdapter(this, cList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        prepareSemesListData()
    }

    private fun prepareSemesListData() {
        cList.add(Others("Find a simple Interest ", R.drawable.a))
        cList.add(Others("Unit I", R.drawable.b))
        cList.add(Others("Unit II", R.drawable.b))
        cList.add(Others("Unit III", R.drawable.d))
        cList.add(Others("Unit IV", R.drawable.d))
        adapter.notifyDataSetChanged()
    }
}
