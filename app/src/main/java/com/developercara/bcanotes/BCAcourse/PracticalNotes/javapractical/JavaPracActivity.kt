package com.developercara.bcanotes.BCAcourse.PracticalNotes.javapractical

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developercara.bcanotes.BCAcourse.PracticalNotes.cpluspractical.CplusPracAdapter
import com.developercara.bcanotes.R
import com.developercara.bcanotes.databinding.ActivityJavaPracBinding
import com.developercara.bcanotes.dataclass.Others

class JavaPracActivity : AppCompatActivity() {
    lateinit var binding: ActivityJavaPracBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: JavaPracAdapter
    private val cList = mutableListOf<Others>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJavaPracBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.recyclerc
        adapter = JavaPracAdapter(this, cList)
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