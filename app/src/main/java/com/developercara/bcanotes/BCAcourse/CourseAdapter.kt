package com.developercara.bcanotes.BCAcourse

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


class courseAdapter constructor(private val getActivity: MainActivity, private val courseList: List<Semes>):
    RecyclerView.Adapter<courseAdapter.MyViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list2, parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.semsId.text = courseList[position].name
        holder.semsImage.setImageResource(courseList[position].image)
        holder.cardView.setOnClickListener{
            val intent = when (position) {
                0 -> Intent(getActivity, BcacourseActivity::class.java)
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
        return courseList.size
    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val semsId: TextView = itemView.findViewById(R.id.semsId)
        val semsImage : ImageView = itemView.findViewById(R.id.semsImage)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }
}