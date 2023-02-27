package com.developercara.bcanotes.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.developercara.bcanotes.MainActivity
import com.developercara.bcanotes.R
import com.developercara.bcanotes.dataclass.Semes
import com.developercara.bcanotes.sem1Activity
import com.developercara.bcanotes.sem1subject.MathematicActivity
import com.developercara.bcanotes.sem2Activity

class SemSubAdapter constructor(private val getActivity: sem1Activity, private val SemSubList: List<Semes>):
    RecyclerView.Adapter<SemSubAdapter.MyViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.semsId.text = SemSubList[position].name
        holder.semsImage.setImageResource(SemSubList[position].image)
        holder.cardView.setOnClickListener{
            val intent = when (position) {
                0 -> Intent(getActivity, MathematicActivity::class.java)
                1 -> Intent(getActivity, sem2Activity::class.java)

                // and so on for other positions
                else -> null
            }
            if (intent != null) {
                getActivity.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return SemSubList.size
    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val semsId: TextView = itemView.findViewById(R.id.semsId)
        val semsImage : ImageView = itemView.findViewById(R.id.semsImage)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }
}