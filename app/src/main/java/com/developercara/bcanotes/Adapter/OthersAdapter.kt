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
import com.developercara.bcanotes.compiler.OnlineCompiler
import com.developercara.bcanotes.dataclass.Others
import com.developercara.bcanotes.pomodoro.PomodoroTime
import com.developercara.bcanotes.practicalnotes.PracticalNotes
import com.developercara.bcanotes.quizgame.MainQuiz

class OthersAdapter constructor(private val getActivity: MainActivity, private val otherList: List<Others>):
    RecyclerView.Adapter<OthersAdapter.MyViewHolder>()
{






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.other_item, parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.other_title.text = otherList[position].tilte
        holder.other_image.setImageResource(otherList[position].image)
        holder.other_click.setOnClickListener{
            val intent = when (position) {
                0 -> Intent(getActivity, MainQuiz::class.java)
                1 -> Intent(getActivity, PracticalNotes::class.java)
                2 -> Intent(getActivity, OnlineCompiler::class.java)
                3 -> Intent(getActivity, PomodoroTime::class.java)


                // and so on for other positions
                else -> null
            }
            if (intent != null) {
                getActivity.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return otherList.size
    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val other_title: TextView = itemView.findViewById(R.id.other_title)
        val other_image : ImageView = itemView.findViewById(R.id.other_image)
        val other_click: CardView = itemView.findViewById(R.id.other_click)
    }
}