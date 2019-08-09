package com.seunggom.tripmanager

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seunggom.tripmanager.model.ContentDTO
import kotlinx.android.synthetic.main.activity_detail_trip.*
import java.text.SimpleDateFormat

class DetailTripActivity : AppCompatActivity() {

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
        var timeFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        info_uploadDate.text = timeFormat.format(content.timestamp)

        info_recycler.layoutManager = LinearLayoutManager(this)
        info_recycler.adapter = imageRecyclerViewAdapter(content.regionName!!)

    }

    inner class imageRecyclerViewAdapter(val region: ArrayList<RegionNameAndPhoto>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun getItemCount(): Int {
            val a = region.size
            return a
        }

        override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {

        }

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(p0.context).inflate(R.layout.detail_trip_images, p0, false)
            return CustomViewHolder(view)
        }

        inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        }

    }
}
