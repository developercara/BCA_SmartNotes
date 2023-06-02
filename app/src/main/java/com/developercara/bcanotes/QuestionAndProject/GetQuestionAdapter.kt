package com.developercara.bcanotes.inappPurchase

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
import com.developercara.bcanotes.dataclass.GetquestionDataClass
import com.developercara.bcanotes.testing.testing

class getQuestionAdapter constructor(private val getActivity: MainActivity, private val purchaseList: List<GetquestionDataClass>):
    RecyclerView.Adapter<getQuestionAdapter.MyViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.getquestionitem, parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.semsId.text = purchaseList[position].name
        holder.semsImage.setImageResource(purchaseList[position].image)
        holder.cardView.setOnClickListener{
            val intent = when (position) {
                0 -> Intent(getActivity, testing::class.java)


                // and so on for other positions
                else -> null
            }
            if (intent != null) {
                getActivity.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return purchaseList.size
    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val semsId: TextView = itemView.findViewById(R.id.semsId)
        val semsImage : ImageView = itemView.findViewById(R.id.semsImage)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }
}