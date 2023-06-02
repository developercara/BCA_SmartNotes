package com.developercara.bcanotes.BCAcourse.PracticalNotes

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.developercara.bcanotes.BCAcourse.PracticalNotes.cpluspractical.CplusPracActivity
import com.developercara.bcanotes.BCAcourse.PracticalNotes.cprogramPractical.CpPracActivity
import com.developercara.bcanotes.BCAcourse.PracticalNotes.javapractical.JavaPracActivity
import com.developercara.bcanotes.BCAcourse.PracticalNotes.python.pythonPracActivity
import com.developercara.bcanotes.R

import com.developercara.bcanotes.dataclass.Semes
import com.developercara.bcanotes.testing.testing


class practicalAdapter constructor(private val getActivity: PracticalNotes, private val semesList: List<Semes>):
    RecyclerView.Adapter<practicalAdapter.MyViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.semsId.text = semesList[position].name
        holder.semsImage.setImageResource(semesList[position].image)
        holder.cardView.setOnClickListener{
            val intent = when (position) {
                0 -> Intent(getActivity, CpPracActivity::class.java)
                1 -> Intent(getActivity, CplusPracActivity::class.java)
                2 -> Intent(getActivity, JavaPracActivity::class.java)
                3 -> Intent(getActivity, pythonPracActivity::class.java)

                // and so on for other positions
                else -> null
            }
            if (intent != null) {
                getActivity.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return semesList.size
    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val semsId: TextView = itemView.findViewById(R.id.semsId)
        val semsImage : ImageView = itemView.findViewById(R.id.semsImage)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }
}