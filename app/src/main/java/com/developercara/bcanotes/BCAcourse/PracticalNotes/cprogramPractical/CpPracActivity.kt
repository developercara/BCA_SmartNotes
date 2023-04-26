package com.developercara.bcanotes.BCAcourse.PracticalNotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developercara.bcanotes.BCAcourse.sem2.cp2.Cp2Adapter
import com.developercara.bcanotes.R
import com.developercara.bcanotes.databinding.ActivityCpPracBinding
import com.developercara.bcanotes.dataclass.Others

class CpPracActivity : AppCompatActivity() {
    lateinit var binding: ActivityCpPracBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CpPratical
    private val cList = mutableListOf<Others>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCpPracBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.recyclerc
        adapter = CpPratical(this, cList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        prepareSemesListData()
    }

    private fun prepareSemesListData() {
        cList.add(Others("Find a simple Interest lollllllllllllll  lllll  lllllllll  lllllllhjkdhserta lolllllllllllllllllllllllllllllllllllhjkdhserta", R.drawable.a))
        cList.add(Others("Unit I", R.drawable.b))
        cList.add(Others("Unit II", R.drawable.b))
        cList.add(Others("Unit III", R.drawable.d))
        cList.add(Others("Unit IV", R.drawable.d))
        adapter.notifyDataSetChanged()
    }
}
