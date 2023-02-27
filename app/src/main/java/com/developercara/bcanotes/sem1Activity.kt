package com.developercara.bcanotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developercara.bcanotes.Adapter.SemSubAdapter
import com.developercara.bcanotes.Adapter.SemesAdapter
import com.developercara.bcanotes.databinding.ActivityMain2Binding
import com.developercara.bcanotes.dataclass.Semes

class sem1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private var recyclerView : RecyclerView?=null
    private var SemSubAdapter: SemSubAdapter?=null
    private var SemSubList = mutableListOf<Semes>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //SEMESTER GRID VIEW
        SemSubList =ArrayList()
        recyclerView = binding.recycleSEMSUB as RecyclerView
        SemSubAdapter = SemSubAdapter(this@sem1Activity,SemSubList)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this,2)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = SemSubAdapter
        prepareSemesListData()
    }
    private  fun prepareSemesListData(){
        var subject =Semes("Mathematics",R.drawable.a)
        SemSubList.add(subject)
        subject =Semes("Web Technologies",R.drawable.a)
        SemSubList.add(subject)
        subject =Semes("C programming",R.drawable.a)
        SemSubList.add(subject)
        subject =Semes("English Language Skills",R.drawable.a)
        SemSubList.add(subject)
        subject =Semes("Introduction To Computer",R.drawable.a)
        SemSubList.add(subject)


        SemSubAdapter!!.notifyDataSetChanged()

    }
}