package com.seunggom.tripmanager

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.content.Context
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.add_region.view.*

class AddRegionViewAdapter(val context: Context, val list: ArrayList<addRegionData>) : RecyclerView.Adapter<AddRegionViewAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.add_region, p0, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {
        p0.bind(list[p1], context)
    }

    override fun getItemCount(): Int {
        return list.size
    }



    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val a = itemView.findViewById<TextView>(R.id.name1text)
        val b = itemView.findViewById<TextView>(R.id.name2text)


        fun bind(list: addRegionData, context: Context) {
            a.text = list.name1
            b.text = list.name2
            var count = list.imageUri!!.size
            for (i in 0..count-1) {
                when (i) {
                    0 -> {
                        itemView.imageView1.setImageURI(list.imageUri!!.elementAt(i))
                        itemView.imageView1.visibility = View.VISIBLE
                    }
                    1 -> {
                        itemView.imageView2.setImageURI(list.imageUri!!.elementAt(i))
                        itemView.imageView2.visibility = View.VISIBLE
                    }
                    2 -> {
                        itemView.imageView3.setImageURI(list.imageUri!!.elementAt(i))
                        itemView.imageView3.visibility = View.VISIBLE
                    }
                    3 -> {
                        itemView.imageView4.setImageURI(list.imageUri!!.elementAt(i))
                        itemView.imageView4.visibility = View.VISIBLE
                    }
                    4 -> {
                        itemView.imageView5.setImageURI(list.imageUri!!.elementAt(i))
                        itemView.imageView5.visibility = View.VISIBLE
                    }
                    5 -> {
                        itemView.imageView6.setImageURI(list.imageUri!!.elementAt(i))
                        itemView.imageView6.visibility = View.VISIBLE
                    }
                    6 -> {
                        itemView.imageView7.setImageURI(list.imageUri!!.elementAt(i))
                        itemView.imageView7.visibility = View.VISIBLE
                    }
                    7 -> {
                        itemView.imageView8.setImageURI(list.imageUri!!.elementAt(i))
                        itemView.imageView8.visibility = View.VISIBLE
                    }
                    8 -> {
                        itemView.imageView9.setImageURI(list.imageUri!!.elementAt(i))
                        itemView.imageView9.visibility = View.VISIBLE
                    }
                }

            }
        }
    }
}