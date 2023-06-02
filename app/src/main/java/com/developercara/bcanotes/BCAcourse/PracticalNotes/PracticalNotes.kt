package com.developercara.bcanotes.BCAcourse.PracticalNotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developercara.bcanotes.R
import com.developercara.bcanotes.databinding.ActivityPracticalNotesBinding
import com.developercara.bcanotes.dataclass.Semes

class PracticalNotes : AppCompatActivity() {
    private var recyclerView : RecyclerView?=null
    private var SemesAdapter: practicalAdapter?=null
    private var semesList = mutableListOf<Semes>()
    lateinit var binding: ActivityPracticalNotesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticalNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        semesList =ArrayList()
        recyclerView = binding.recyclemain
        SemesAdapter = practicalAdapter(this@PracticalNotes,semesList)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this,2)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = SemesAdapter
        prepareSemesListData()
    }

    private  fun prepareSemesListData(){
        var semseters =Semes("C PROGRAMMING",R.drawable.sems1)
        semesList.add(semseters)
        semseters =Semes("C++",R.drawable.sems2)
        semesList.add(semseters)
        semseters =Semes("JAVA",R.drawable.sems6)
        semesList.add(semseters)
        semseters =Semes("PYTHON",R.drawable.sems6)
        semesList.add(semseters)
        semseters =Semes("WEB TECH",R.drawable.sems3)
        semesList.add(semseters)
        semseters =Semes("VB.NET",R.drawable.sems4)
        semesList.add(semseters)
        semseters =Semes("ASP.NET",R.drawable.sems5)
        semesList.add(semseters)
        semseters =Semes("DBMS",R.drawable.sems6)
        semesList.add(semseters)


        SemesAdapter!!.notifyDataSetChanged()

    }
}