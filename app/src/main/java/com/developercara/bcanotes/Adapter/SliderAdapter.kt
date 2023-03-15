package com.developercara.bcanotes.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.developercara.bcanotes.R
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter ( private val images: List<Int>,private val context: Context) : SliderViewAdapter<SliderAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image_slider, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(viewHolder: Holder, position: Int) {
        viewHolder.imageView.setImageResource(images[position])
        viewHolder.imageView.setOnClickListener {
            val intent: Intent
            when (position) {
                0 -> {
                    intent = Intent(context, sem2Activity::class.java)
                    context.startActivity(intent)
                }
                1 -> {
                    intent = Intent(context, sem2Activity::class.java)
                    context.startActivity(intent)
                }
                2 -> {
                    intent = Intent(context, sem2Activity::class.java)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun getCount(): Int {
        return images.size
    }

    inner class Holder(itemView: View) : SliderViewAdapter.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
    }


}