package com.seunggom.tripmanager

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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


    }


}
