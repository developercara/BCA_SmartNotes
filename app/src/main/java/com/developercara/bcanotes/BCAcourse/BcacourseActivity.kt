package com.developercara.bcanotes.BCAcourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developercara.bcanotes.R
import com.developercara.bcanotes.databinding.ActivityBcacourseBinding
import com.developercara.bcanotes.dataclass.Semes

class BcacourseActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var semesAdapter: SemesAdapter
    private var semesList = mutableListOf<Semes>()
    lateinit var binding: ActivityBcacourseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBcacourseBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //// SEMESTER GRID VIEW
        semesList =ArrayList()
        recyclerView = binding.recyclemain
        semesAdapter = SemesAdapter(this@BcacourseActivity,semesList)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this,2)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = semesAdapter
        prepareSemesListData()
    }
    private fun prepareSemesListData() {
        var semseters =Semes("1st Semester",R.drawable.sems1)
        semesList.add(semseters)
        semseters =Semes("2nd Semester",R.drawable.sems2)
        semesList.add(semseters)
        semseters =Semes("3rd Semester",R.drawable.sems3)
        semesList.add(semseters)
        semseters =Semes("4th Semester",R.drawable.sems4)
        semesList.add(semseters)
        semseters =Semes("5th Semester",R.drawable.sems5)
        semesList.add(semseters)
        semseters =Semes("Internship",R.drawable.sems6)
        semesList.add(semseters)

        semesAdapter.notifyDataSetChanged()
    }
}