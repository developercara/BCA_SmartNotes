package com.developercara.bcanotes.quizgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developercara.bcanotes.R
import com.developercara.bcanotes.databinding.ActivityMainQuizBinding
import com.developercara.bcanotes.dataclass.Semes

class MainQuiz : AppCompatActivity() {
    lateinit var binding: ActivityMainQuizBinding
    private var recyclerView : RecyclerView?=null
    private var SemesAdapter: mainQuizAdapter?=null
    private var semesList = mutableListOf<Semes>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)


        semesList =ArrayList()
        recyclerView = binding.quizCato as RecyclerView
        SemesAdapter = mainQuizAdapter(this@MainQuiz,semesList)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this,2)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = SemesAdapter
        prepareSemesListData()
    }
    private  fun prepareSemesListData(){
        var semseters =Semes("JAVA",R.drawable.sems1)
        semesList.add(semseters)
        semseters =Semes("PYTHON",R.drawable.sems2)
        semesList.add(semseters)
        semseters =Semes("C PROGRAMMING",R.drawable.sems3)
        semesList.add(semseters)
        semseters =Semes("C++",R.drawable.sems4)
        semesList.add(semseters)
        semseters =Semes("KOTLIN",R.drawable.sems5)
        semesList.add(semseters)
        semseters =Semes("C SHARP", R.drawable.sems6)
        semesList.add(semseters)
        semseters =Semes("JAVA SCRIPT", R.drawable.sems6)
        semesList.add(semseters)
        semseters =Semes("SQL", R.drawable.sems6)
        semesList.add(semseters)

        SemesAdapter!!.notifyDataSetChanged()

    }
}