package com.developercara.bcanotes.BCAcourse

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.developercara.bcanotes.R
import com.developercara.bcanotes.dataclass.Semes
import com.developercara.bcanotes.BCAcourse.sem1subject.cprogramming.CprogramActivity
import com.developercara.bcanotes.BCAcourse.sem1subject.els.ELSActivity
import com.developercara.bcanotes.BCAcourse.sem1subject.itc.ITCActivity
import com.developercara.bcanotes.BCAcourse.sem1subject.mathematics1.MathsActivity
import com.developercara.bcanotes.BCAcourse.sem1subject.webtechnology.webtechActivity

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
                0 -> Intent(getActivity, MathsActivity::class.java)
                1 -> Intent(getActivity, webtechActivity::class.java)
                2 -> Intent(getActivity, CprogramActivity::class.java)
                3 -> Intent(getActivity, ELSActivity::class.java)
                4 -> Intent(getActivity, ITCActivity::class.java)



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