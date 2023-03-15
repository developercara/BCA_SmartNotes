package com.developercara.bcanotes.BCAcourse.sem1subject.itc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developercara.bcanotes.R
import com.developercara.bcanotes.databinding.ActivityItcactivityBinding
import com.developercara.bcanotes.dataclass.Others

class ITCActivity : AppCompatActivity() {
    lateinit var binding: ActivityItcactivityBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ITCADAPTER
    private val cList = mutableListOf<Others>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityItcactivityBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        recyclerView = binding.recyclerc
        adapter = ITCADAPTER(this, cList)
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
