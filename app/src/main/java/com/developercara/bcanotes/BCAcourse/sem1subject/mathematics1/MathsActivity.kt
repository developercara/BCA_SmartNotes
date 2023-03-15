package com.developercara.bcanotes.BCAcourse.sem1subject.mathematics1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developercara.bcanotes.R
import com.developercara.bcanotes.databinding.ActivityMathsBinding
import com.developercara.bcanotes.dataclass.Others

class MathsActivity : AppCompatActivity() {
    lateinit var binding: ActivityMathsBinding
    private var recyclerView3 : RecyclerView?=null
    private var mathsAdapter: MathAdapter?=null
    private var mathList = mutableListOf<Others>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMathsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mathList =ArrayList()
        recyclerView3 = binding.recyclerc as RecyclerView
        mathsAdapter = MathAdapter(this@MathsActivity,mathList)
        val layoutManager2: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView3!!.layoutManager = layoutManager2
        recyclerView3!!.adapter = mathsAdapter
        prepareSemesListData2()
    }
    private fun prepareSemesListData2() {
        var others = Others("SYLLABUS",R.drawable.a)
        mathList.add(others)
        others = Others("Unit I",R.drawable.b)
        mathList.add(others)
        others = Others("Unit II",R.drawable.b)
        mathList.add(others)
        others = Others("Unit III",R.drawable.d)
        mathList.add(others)



        mathsAdapter!!.notifyDataSetChanged()
    }
}