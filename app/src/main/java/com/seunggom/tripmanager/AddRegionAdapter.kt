package com.seunggom.tripmanager

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.content.Context
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

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
        val a = itemView.findViewById<TextView>(R.id.name)
        val d = itemView.findViewById<ImageView>(R.id.imageView)

        fun bind(list: addRegionData, context: Context) {
            a.text = list.name1
        }
    }
}