package com.developercara.bcanotes.BCAcourse.sem1subject.cprogramming

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developercara.bcanotes.R
import com.developercara.bcanotes.databinding.ActivityCprogramBinding

import com.developercara.bcanotes.dataclass.Others


class CprogramActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCprogramBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Cadapter
    private val cList = mutableListOf<Others>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCprogramBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerc
        adapter = Cadapter(this, cList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        prepareSemesListData()
    }

    private fun prepareSemesListData() {
        cList.add(Others("SYLLABUS", R.drawable.a))
        cList.add(Others("Unit I", R.drawable.b))
        cList.add(Others("Unit II", R.drawable.b))
        cList.add(Others("Unit III", R.drawable.d))
        adapter.notifyDataSetChanged()
    }
}
