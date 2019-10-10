package com.seunggom.tripmanager

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
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
        p0.editImageText(list[p1])
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
                        Glide.with(itemView).load(list.imageUri!!.elementAt(i)).into(itemView.ad_imageView1)
                        itemView.imageLayout1.visibility = View.VISIBLE
                    }
                    1 -> {
                        //itemView.imageView2.setImageURI(list.imageUri!!.elementAt(i))
                        Glide.with(itemView).load(list.imageUri!!.elementAt(i)).into(itemView.ad_imageView2)
                        itemView.imageLayout2.visibility = View.VISIBLE
                    }
                    2 -> {
                        //itemView.imageView3.setImageURI(list.imageUri!!.elementAt(i))
                        Glide.with(itemView).load(list.imageUri!!.elementAt(i)).into(itemView.ad_imageView3)
                        itemView.imageLayout3.visibility = View.VISIBLE
                    }
                    3 -> {
                        //itemView.imageView4.setImageURI(list.imageUri!!.elementAt(i))
                        Glide.with(itemView).load(list.imageUri!!.elementAt(i)).into(itemView.ad_imageView4)
                        itemView.imageLayout4.visibility = View.VISIBLE
                    }
                    4 -> {
                        //itemView.imageView5.setImageURI(list.imageUri!!.elementAt(i))
                        Glide.with(itemView).load(list.imageUri!!.elementAt(i)).into(itemView.ad_imageView5)
                        itemView.imageLayout5.visibility = View.VISIBLE
                    }
                    5 -> {
                        //itemView.imageView6.setImageURI(list.imageUri!!.elementAt(i))
                        Glide.with(itemView).load(list.imageUri!!.elementAt(i)).into(itemView.ad_imageView6)
                        itemView.imageLayout6.visibility = View.VISIBLE
                    }
                    6 -> {
                        //itemView.imageView7.setImageURI(list.imageUri!!.elementAt(i))
                        Glide.with(itemView).load(list.imageUri!!.elementAt(i)).into(itemView.ad_imageView7)
                        itemView.imageLayout7.visibility = View.VISIBLE
                    }
                    7 -> {
                        //itemView.imageView8.setImageURI(list.imageUri!!.elementAt(i))
                        Glide.with(itemView).load(list.imageUri!!.elementAt(i)).into(itemView.ad_imageView8)
                        itemView.imageLayout8.visibility = View.VISIBLE
                    }
                    8 -> {
                        //itemView.imageView9.setImageURI(list.imageUri!!.elementAt(i))
                        Glide.with(itemView).load(list.imageUri!!.elementAt(i)).into(itemView.ad_imageView9)
                        itemView.imageLayout9.visibility = View.VISIBLE
                    }
                }

            }
        }

        fun editImageText(list: addRegionData) {
            val watcher = object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if(s.hashCode() == itemView.imageText1.text.hashCode()) list.imageText!![0] = s.toString()
                    else if(s.hashCode() == itemView.imageText2.text.hashCode()) list.imageText!![1] = s.toString()
                    else if(s.hashCode() == itemView.imageText3.text.hashCode()) list.imageText!![2] = s.toString()
                    else if(s.hashCode() == itemView.imageText4.text.hashCode()) list.imageText!![3] = s.toString()
                    else if(s.hashCode() == itemView.imageText5.text.hashCode()) list.imageText!![4] = s.toString()
                    else if(s.hashCode() == itemView.imageText6.text.hashCode()) list.imageText!![5] = s.toString()
                    else if(s.hashCode() == itemView.imageText7.text.hashCode()) list.imageText!![6] = s.toString()
                    else if(s.hashCode() == itemView.imageText8.text.hashCode()) list.imageText!![7] = s.toString()
                    else if(s.hashCode() == itemView.imageText9.text.hashCode()) list.imageText!![8] = s.toString()

                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }
            }
            itemView.imageText1.addTextChangedListener(watcher)
            itemView.imageText2.addTextChangedListener(watcher)
            itemView.imageText3.addTextChangedListener(watcher)
            itemView.imageText4.addTextChangedListener(watcher)
            itemView.imageText5.addTextChangedListener(watcher)
            itemView.imageText6.addTextChangedListener(watcher)
            itemView.imageText7.addTextChangedListener(watcher)
            itemView.imageText8.addTextChangedListener(watcher)
            itemView.imageText9.addTextChangedListener(watcher)



        }


    }


}
// http://blog.naver.com/PostView.nhn?blogId=artisan_ryu&logNo=220797549440&parentCategoryNo=&categoryNo=56&viewDate=&isShowPopularPosts=false&from=postView
// http://www.masterqna.com/android/67562/recyclerview%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%B4%EC%84%9C-edittext%EB%A5%BC-gettext%ED%95%A0%EB%A0%A4%EA%B3%A0%ED%95%98%EB%8A%94%EB%8D%B0%EC%9A%94