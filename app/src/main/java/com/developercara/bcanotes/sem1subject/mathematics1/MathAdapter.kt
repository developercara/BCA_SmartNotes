package com.developercara.bcanotes.sem1subject.mathematics1

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.developercara.bcanotes.R
import com.developercara.bcanotes.dataclass.Others
import com.developercara.bcanotes.sem1subject.cprogramming.CprogramActivity
import com.developercara.bcanotes.sem1subject.cprogramming.CprogrammingUnit1
import com.developercara.bcanotes.sem1subject.cprogramming.CprogrammingUnit2
import com.developercara.bcanotes.sem1subject.cprogramming.CprogrammingUnit3

class MathAdapter constructor(private val getActivity: MathsActivity, private val mathList: List<Others>):
    RecyclerView.Adapter<MathAdapter.MyViewHolder>()
{






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.other_item, parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.other_title.text = mathList[position].tilte
        holder.other_image.setImageResource(mathList[position].image)
        holder.other_click.setOnClickListener{
            val intent = when (position) {
                0 -> Intent(getActivity, mathematicsSyllabus::class.java)
                1 -> Intent(getActivity, CprogrammingUnit1::class.java)
                2 -> Intent(getActivity, CprogrammingUnit2::class.java)
                3 -> Intent(getActivity, CprogrammingUnit3::class.java)



                // and so on for other positions
                else -> null
            }
            if (intent != null) {
                getActivity.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return mathList.size
    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val other_title: TextView = itemView.findViewById(R.id.other_title)
        val other_image : ImageView = itemView.findViewById(R.id.other_image)
        val other_click: CardView = itemView.findViewById(R.id.other_click)
    }
}