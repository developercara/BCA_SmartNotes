package com.developercara.bcanotes.BCAcourse.sem2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developercara.bcanotes.BCAcourse.SemSubAdapter
import com.developercara.bcanotes.R
import com.developercara.bcanotes.databinding.ActivityMain2Binding
import com.developercara.bcanotes.dataclass.Semes

class Sems2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private var recyclerView : RecyclerView?=null
    private var Sem2Adapter: sem2Adapter?=null
    private var SemSubList = mutableListOf<Semes>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //SEMESTER GRID VIEW
        SemSubList =ArrayList()
        recyclerView = binding.recycleSEMSUB as RecyclerView
        Sem2Adapter = sem2Adapter(this@Sems2Activity,SemSubList)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this,2)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = Sem2Adapter
        prepareSemesListData()
    }
    private  fun prepareSemesListData(){
        var subject = Semes("C Programming-II", R.drawable.sems5)
        SemSubList.add(subject)
        subject = Semes("Computer Organisation", R.drawable.sems4)
        SemSubList.add(subject)
        subject = Semes("Mathematics-II", R.drawable.sems3)
        SemSubList.add(subject)
        subject = Semes("Environmental Studies", R.drawable.sems2)
        SemSubList.add(subject)
        subject = Semes("Principles of Economics", R.drawable.sems1)
        SemSubList.add(subject)


        Sem2Adapter!!.notifyDataSetChanged()

    }
}