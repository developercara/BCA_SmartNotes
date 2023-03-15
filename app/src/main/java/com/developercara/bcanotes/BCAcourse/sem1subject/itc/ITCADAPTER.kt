package com.developercara.bcanotes.BCAcourse.sem1subject.itc

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
import com.developercara.bcanotes.BCAcourse.sem1subject.cprogramming.CprogrammingUnit



class ITCADAPTER constructor(
    private val getActivity: ITCActivity,
    private val cList: List<Others>
) : RecyclerView.Adapter<ITCADAPTER.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.other_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.other_title.text = cList[position].tilte
        holder.other_image.setImageResource(cList[position].image)
        holder.other_click.setOnClickListener {
            val intent = Intent(getActivity, ITCSYLLABUS::class.java)

            when (position) {
                0 -> intent.putExtra("unit_number", 0)
                1 -> intent.putExtra("unit_number", 1)
                2 -> intent.putExtra("unit_number", 2)
                3 -> intent.putExtra("unit_number", 3)
            }
            getActivity.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return cList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val other_title: TextView = itemView.findViewById(R.id.other_title)
        val other_image: ImageView = itemView.findViewById(R.id.other_image)
        val other_click: CardView = itemView.findViewById(R.id.other_click)
    }
}
