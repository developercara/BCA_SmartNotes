package com.developercara.bcanotes.BCAcourse.PracticalNotes.cprogramPractical

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.developercara.bcanotes.BCAcourse.sem2.cp2.Cp2ViewerActivity
import com.developercara.bcanotes.R
import com.developercara.bcanotes.dataclass.Others
import com.developercara.bcanotes.dataclass.Others2


class CpPratical constructor(

    private val getActivity: CpPracActivity,
    private val cList: List<Others2>
) : RecyclerView.Adapter<CpPratical.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.other_item2, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.other_title.text = cList[position].tilte

        holder.other_click.setOnClickListener {
            val intent = Intent(getActivity, CpPracViewer::class.java)

            when (position) {
                0 -> intent.putExtra("unit_number", 0)
                1 -> intent.putExtra("unit_number", 1)
                2 -> intent.putExtra("unit_number", 2)
                3 -> intent.putExtra("unit_number", 3)
                4 -> intent.putExtra("unit_number", 4)
                5 -> intent.putExtra("unit_number", 5)
                6 -> intent.putExtra("unit_number", 6)
                7 -> intent.putExtra("unit_number", 7)
                8 -> intent.putExtra("unit_number", 8)
                9 -> intent.putExtra("unit_number", 9)
                10 -> intent.putExtra("unit_number", 10)
                11 -> intent.putExtra("unit_number", 11)
                12 -> intent.putExtra("unit_number", 12)
                13 -> intent.putExtra("unit_number", 13)
                14 -> intent.putExtra("unit_number", 14)
                15 -> intent.putExtra("unit_number", 15)
            }
            getActivity.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return cList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val other_title: TextView = itemView.findViewById(R.id.other_title)
        val other_click: CardView = itemView.findViewById(R.id.other_click)
    }
}