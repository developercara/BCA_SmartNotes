package com.developercara.bcanotes.sem1subject.cprogramming

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developercara.bcanotes.Adapter.Cadapter
import com.developercara.bcanotes.Adapter.OthersAdapter
import com.developercara.bcanotes.Adapter.SemesAdapter
import com.developercara.bcanotes.R
import com.developercara.bcanotes.databinding.ActivityCprogramBinding

import com.developercara.bcanotes.dataclass.Others
import com.developercara.bcanotes.dataclass.Semes

class CprogramActivity : AppCompatActivity() {
    lateinit var binding: ActivityCprogramBinding
    private var recyclerView3 : RecyclerView?=null
    private var Cadapter: Cadapter?=null
    private var cList = mutableListOf<Others>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCprogramBinding.inflate(layoutInflater)
        setContentView(binding.root)


        cList =ArrayList()
        recyclerView3 = binding.recyclerc as RecyclerView
        Cadapter = Cadapter(this@CprogramActivity,cList)
        val layoutManager2: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView3!!.layoutManager = layoutManager2
        recyclerView3!!.adapter = Cadapter
        prepareSemesListData2()
    }
    private fun prepareSemesListData2() {
        var others = Others("SYLLABUS",R.drawable.a)
        cList.add(others)
        others = Others("Unit I",R.drawable.b)
        cList.add(others)
        others = Others("Unit II",R.drawable.b)
        cList.add(others)
        others = Others("Unit III",R.drawable.d)
        cList.add(others)



        Cadapter!!.notifyDataSetChanged()
    }
}