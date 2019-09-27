package com.seunggom.tripmanager

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
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

                    // 바로 이미지뷰에 띄우지 말고 glide를 통해서 띄우도록 수정!
                    0 -> {
                        //itemView.imageView1.setImageURI(list.imageUri!!.elementAt(i))
                        Glide.with(itemView).load(list.imageUri!!.elementAt(i)).into(itemView.imageView1)
                        itemView.imageLayout1.visibility = View.VISIBLE
                    }
                    1 -> {
                        //itemView.imageView2.setImageURI(list.imageUri!!.elementAt(i))
                        Glide.with(itemView).load(list.imageUri!!.elementAt(i)).into(itemView.imageView2)
                        itemView.imageLayout2.visibility = View.VISIBLE
                    }
                    2 -> {
                        //itemView.imageView3.setImageURI(list.imageUri!!.elementAt(i))
                        Glide.with(itemView).load(list.imageUri!!.elementAt(i)).into(itemView.imageView3)
                        itemView.imageLayout3.visibility = View.VISIBLE
                    }
                    3 -> {
                        //itemView.imageView4.setImageURI(list.imageUri!!.elementAt(i))
                        Glide.with(itemView).load(list.imageUri!!.elementAt(i)).into(itemView.imageView4)
                        itemView.imageLayout4.visibility = View.VISIBLE
                    }
                    4 -> {
                        //itemView.imageView5.setImageURI(list.imageUri!!.elementAt(i))
                        Glide.with(itemView).load(list.imageUri!!.elementAt(i)).into(itemView.imageView5)
                        itemView.imageLayout5.visibility = View.VISIBLE
                    }
                    5 -> {
                        //itemView.imageView6.setImageURI(list.imageUri!!.elementAt(i))
                        Glide.with(itemView).load(list.imageUri!!.elementAt(i)).into(itemView.imageView6)
                        itemView.imageLayout6.visibility = View.VISIBLE
                    }
                    6 -> {
                        //itemView.imageView7.setImageURI(list.imageUri!!.elementAt(i))
                        Glide.with(itemView).load(list.imageUri!!.elementAt(i)).into(itemView.imageView7)
                        itemView.imageLayout7.visibility = View.VISIBLE
                    }
                    7 -> {
                        //itemView.imageView8.setImageURI(list.imageUri!!.elementAt(i))
                        Glide.with(itemView).load(list.imageUri!!.elementAt(i)).into(itemView.imageView8)
                        itemView.imageLayout8.visibility = View.VISIBLE
                    }
                    8 -> {
                        //itemView.imageView9.setImageURI(list.imageUri!!.elementAt(i))
                        Glide.with(itemView).load(list.imageUri!!.elementAt(i)).into(itemView.imageView9)
                        itemView.imageLayout9.visibility = View.VISIBLE
                    }
                }

            }
        }
    }
}