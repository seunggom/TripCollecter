package com.seunggom.tripmanager

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.storage.FirebaseStorage
import com.seunggom.tripmanager.model.ContentDTO
import kotlinx.android.synthetic.main.activity_detail_trip.*
import kotlinx.android.synthetic.main.detail_trip_images.view.*
import java.text.SimpleDateFormat
import java.util.*

class DetailTripActivity : AppCompatActivity() {

    val storage = FirebaseStorage.getInstance("gs://tripcollecter-6499f.appspot.com")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_trip)
        intent = getIntent()
        val content = intent.getSerializableExtra("trip_info") as ContentDTO

        var date = content.startDate + " ~ " + content.endDate
        info_date.text = date
        info_title.text = content.title
        info_rating.rating = content.rating.toFloat()
        info_user.text = content.userId
        info_explain.text = content.explain
        var timeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        info_uploadDate.text = timeFormat.format(content.timestamp)

        info_recycler.layoutManager = LinearLayoutManager(this)
        info_recycler.adapter = imageRecyclerViewAdapter(content.regionName!!, content.timestamp!!)

    }

    inner class imageRecyclerViewAdapter(val region: ArrayList<RegionNameAndPhoto>, val time: Long) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun getItemCount(): Int {
            val a = region.size
            return a
        }

        override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
            val viewHolder = (p0 as CustomViewHolder).itemView
            viewHolder.name1_dtl.text = region[p1].name1
            viewHolder.name2_dtl.text = region[p1].name2

            var timeFormat = SimpleDateFormat("yyyyMMdd_HHmmss").format(time)  //13시인데 그냥 01시라고 뜬다 해결 필요!



            val count = region[p1].photo!!.size
            var imageNum = 1
            for (j in 0..count-1) {
                val imageFileName = "JPEG_" + timeFormat + "_" + region[p1].name1 + "_" + region[p1].name2 + "_" + imageNum +"_.png"
                imageNum = imageNum + 1
                val storageRef = storage?.reference?.child("images")!!.child(imageFileName)
// https://firebase.google.com/docs/storage/android/download-files?authuser=0
                /*storageRef!!.downloadUrl.addOnCompleteListener {task ->
                    var realUri = task.result
                    when(j) {
                        0 -> {
                            viewHolder.imageView1.setImageURI(realUri)
                            viewHolder.imageView1.visibility = View.VISIBLE
                        }
                        1 -> {
                            viewHolder.imageView2.setImageURI(realUri)
                            viewHolder.imageView2.visibility = View.VISIBLE
                        }
                        2 -> {
                            viewHolder.imageView3.setImageURI(realUri)
                            viewHolder.imageView3.visibility = View.VISIBLE
                        }
                        3 -> {
                            viewHolder.imageView4.setImageURI(realUri)
                            viewHolder.imageView4.visibility = View.VISIBLE
                        }
                        4 -> {
                            viewHolder.imageView5.setImageURI(realUri)
                            viewHolder.imageView5.visibility = View.VISIBLE
                        }
                        5 -> {
                            viewHolder.imageView6.setImageURI(realUri)
                            viewHolder.imageView6.visibility = View.VISIBLE
                        }
                        6 -> {
                            viewHolder.imageView7.setImageURI(realUri)
                            viewHolder.imageView7.visibility = View.VISIBLE
                        }
                        7 -> {
                            viewHolder.imageView8.setImageURI(realUri)
                            viewHolder.imageView8.visibility = View.VISIBLE
                        }
                        8 -> {
                            viewHolder.imageView9.setImageURI(realUri)
                            viewHolder.imageView9.visibility = View.VISIBLE
                        }
                    }

                }*/

            }
        }

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(p0.context).inflate(R.layout.detail_trip_images, p0, false)
            return CustomViewHolder(view)
        }

        inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        }

    }
}