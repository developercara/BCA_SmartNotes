package com.developercara.bcanotes.quizgame

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
import com.developercara.bcanotes.quizgame.categories.cplusActivity
import com.developercara.bcanotes.quizgame.categories.cprogramActivity
import com.developercara.bcanotes.quizgame.categories.pythonActivity

class mainQuizAdapter(private val getActivity: MainQuiz, private val quizList: List<Semes>):
    RecyclerView.Adapter<mainQuizAdapter.MyViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.semsId.text = quizList[position].name
        holder.semsImage.setImageResource(quizList[position].image)
        holder.cardView.setOnClickListener{
            val intent = when (position) {
                0 -> Intent(getActivity, QuizGame::class.java)
                1 -> Intent(getActivity, pythonActivity::class.java)
                2 -> Intent(getActivity, cprogramActivity::class.java)
                3 -> Intent(getActivity, cplusActivity::class.java)

                // and so on for other positions
                else -> null
            }
            if (intent != null) {
                getActivity.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return quizList.size
    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val semsId: TextView = itemView.findViewById(R.id.semsId)
        val semsImage : ImageView = itemView.findViewById(R.id.semsImage)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }
}