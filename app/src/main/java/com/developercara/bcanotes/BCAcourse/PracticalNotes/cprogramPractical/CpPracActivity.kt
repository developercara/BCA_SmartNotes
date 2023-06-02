package com.developercara.bcanotes.BCAcourse.PracticalNotes.cprogramPractical

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developercara.bcanotes.databinding.ActivityCpPracBinding
import com.developercara.bcanotes.dataclass.Others2

class CpPracActivity : AppCompatActivity() {
    lateinit var binding: ActivityCpPracBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CpPratical
    private val cList = mutableListOf<Others2>()
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
        cList.add(Others2("FIND SIMPLE INTEREST "))
        cList.add(Others2("FIND HIGHEST OF THREE NUMBERS"))
        cList.add(Others2("PRINT RIGHT TRIANGLE STAR PATTERN"))
        cList.add(Others2("PRINT REVERSE RIGHT TRIANGLE STAR PATTERN"))
        cList.add(Others2("PRINT STAR PYRAMID"))
        cList.add(Others2("CREATE MENU DRIVEN FOR FACTORIAL,EVEN OR ODD AND PRIME NUMBER"))
        cList.add(Others2("FIND A GRADE OF A STUDENT"))
        cList.add(Others2("SWAP TWO NUMBERS WITHOUT USING THIRD VARIABLE"))
        cList.add(Others2("CREATE A CALCULATOR"))
        cList.add(Others2("CALCULATE GCD AND LCM OF GIVEN TWO NUMBERS"))
        cList.add(Others2("FIBONACCI SERIES"))
        cList.add(Others2("PRINT PRIME NUMBERS BETWEEN M AND N"))
        cList.add(Others2("REVERSE A GIVEN INTEGER NUMBER AND  CHECKS WHETHER THE NUMBER IN PALIDROME OR NOT"))
        cList.add(Others2("CREATE A SQUARE STAR PATTERN"))
        cList.add(Others2("PRINT HALLOW SQUARE STAR PATTERN"))
        cList.add(Others2("PRINT HALLOW SQUARE STAR PATTERN"))
        adapter.notifyDataSetChanged()
    }
}
