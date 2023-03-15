package com.developercara.bcanotes.practicalnotes

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.developercara.bcanotes.R


class CategoryAdapter(private val categories: List<Category>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.textitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val categoryTitleTextView = itemView.findViewById<TextView>(R.id.categoryTitleTextView)


        fun bind(category: Category) {
            categoryTitleTextView.text = category.title

            categoryTitleTextView.setOnClickListener {
                val intent = Intent(itemView.context, displayCode::class.java)
                intent.putExtra("code", category.code)
                itemView.context.startActivity(intent)
            }
        }
    }
}
