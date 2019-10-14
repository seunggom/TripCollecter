package com.seunggom.tripmanager

import android.app.Dialog
import android.graphics.Point
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.seunggom.tripmanager.model.ContentDTO
import kotlinx.android.synthetic.main.activity_detail_trip.*
import kotlinx.android.synthetic.main.detail_trip_images.view.*
import kotlinx.android.synthetic.main.extend_image.*
import org.jetbrains.anko.toast
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
        info_recycler.adapter = imageRecyclerViewAdapter(content.regionName!!, content.timestamp!!, content.userId!!)

    }

    inner class imageRecyclerViewAdapter(val region: ArrayList<RegionNameAndPhoto>, val time: Long, val ID: String)
        : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun getItemCount(): Int {
            val a = region.size
            return a
        }

        override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
            val viewHolder = (p0 as CustomViewHolder).itemView
            viewHolder.name1_dtl.text = region[p1].name1
            viewHolder.name2_dtl.text = region[p1].name2

            var screen = windowManager.defaultDisplay
            var screenSize = Point()
            screen.getSize(screenSize)

            var timeFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.KOREA).format(time)



            val count = region[p1].photo!!.size
            var imageNum = 1
            for (j in 0..count-1) {
                val imageFileName = timeFormat + "_" + region[p1].name2 + "_" + imageNum
                imageNum = imageNum + 1
                val storageRef = storage?.reference?.child(ID)!!.child(imageFileName)
// https://firebase.google.com/docs/storage/android/download-files?authuser=0
                storageRef!!.downloadUrl.addOnCompleteListener {task ->
                    var realUri = task.result
                    var imgView : ImageView? = null
                    when(j) {
                        0 -> {
                            viewHolder.detail_CardView1.visibility = View.VISIBLE
                            Glide.with(viewHolder).load(realUri).into(viewHolder.imageView1_dtl)
                            viewHolder.imageView1_dtl.visibility = View.VISIBLE
                            imgView = viewHolder.imageView1_dtl
                            viewHolder.textView1_dtl.text = region[p1].explains!![j]
                            viewHolder.textView1_dtl.visibility = View.VISIBLE
                        }
                        1 -> {
                            viewHolder.detail_CardView2.visibility = View.VISIBLE
                            Glide.with(viewHolder).load(realUri).into(viewHolder.imageView2_dtl)
                            viewHolder.imageView2_dtl.visibility = View.VISIBLE
                            imgView = viewHolder.imageView2_dtl
                            viewHolder.textView2_dtl.text = region[p1].explains!![j]
                            viewHolder.textView2_dtl.visibility = View.VISIBLE
                        }
                        2 -> {
                            viewHolder.detail_CardView3.visibility = View.VISIBLE
                            Glide.with(viewHolder).load(realUri).into(viewHolder.imageView3_dtl)
                            viewHolder.imageView3_dtl.visibility = View.VISIBLE
                            imgView = viewHolder.imageView3_dtl
                            viewHolder.textView3_dtl.text = region[p1].explains!![j]
                            viewHolder.textView3_dtl.visibility = View.VISIBLE
                        }
                        3 -> {
                            viewHolder.detail_CardView4.visibility = View.VISIBLE
                            Glide.with(viewHolder).load(realUri).into(viewHolder.imageView4_dtl)
                            viewHolder.imageView4_dtl.visibility = View.VISIBLE
                            imgView = viewHolder.imageView4_dtl
                            viewHolder.textView4_dtl.text = region[p1].explains!![j]
                            viewHolder.textView4_dtl.visibility = View.VISIBLE
                        }
                        4 -> {
                            viewHolder.detail_CardView5.visibility = View.VISIBLE
                            Glide.with(viewHolder).load(realUri).into(viewHolder.imageView5_dtl)
                            viewHolder.imageView5_dtl.visibility = View.VISIBLE
                            imgView = viewHolder.imageView5_dtl
                            viewHolder.textView5_dtl.text = region[p1].explains!![j]
                            viewHolder.textView5_dtl.visibility = View.VISIBLE
                        }
                        5 -> {
                            viewHolder.detail_CardView6.visibility = View.VISIBLE
                            Glide.with(viewHolder).load(realUri).into(viewHolder.imageView6_dtl)
                            viewHolder.imageView6_dtl.visibility = View.VISIBLE
                            imgView = viewHolder.imageView6_dtl
                            viewHolder.textView6_dtl.text = region[p1].explains!![j]
                            viewHolder.textView6_dtl.visibility = View.VISIBLE
                        }
                        6 -> {
                            viewHolder.detail_CardView7.visibility = View.VISIBLE
                            Glide.with(viewHolder).load(realUri).into(viewHolder.imageView7_dtl)
                            viewHolder.imageView7_dtl.visibility = View.VISIBLE
                            imgView = viewHolder.imageView7_dtl
                            viewHolder.textView7_dtl.text = region[p1].explains!![j]
                            viewHolder.textView7_dtl.visibility = View.VISIBLE
                        }
                        7 -> {
                            viewHolder.detail_CardView8.visibility = View.VISIBLE
                            Glide.with(viewHolder).load(realUri).into(viewHolder.imageView8_dtl)
                            viewHolder.imageView8_dtl.visibility = View.VISIBLE
                            imgView = viewHolder.imageView8_dtl
                            viewHolder.textView8_dtl.text = region[p1].explains!![j]
                            viewHolder.textView8_dtl.visibility = View.VISIBLE
                        }
                        8 -> {
                            viewHolder.detail_CardView9.visibility = View.VISIBLE
                            Glide.with(viewHolder).load(realUri).into(viewHolder.imageView9_dtl)
                            viewHolder.imageView9_dtl.visibility = View.VISIBLE
                            imgView = viewHolder.imageView9_dtl
                            viewHolder.textView9_dtl.text = region[p1].explains!![j]
                            viewHolder.textView9_dtl.visibility = View.VISIBLE
                        }
                    }
                    imgView!!.layoutParams.height = screenSize.x / 2
                    imgView!!.layoutParams.width = screenSize.x / 2
                    imgView!!.requestLayout()
                }

            }

            viewHolder.imageView1_dtl.setOnClickListener {
                toast("image1 clicked")
                val imageFileName = timeFormat + "_" + region[p1].name2 + "_" + 1
                callDialog(imageFileName)
            }
            viewHolder.imageView2_dtl.setOnClickListener {
                toast("image2 clicked")
                val imageFileName = timeFormat + "_" + region[p1].name2 + "_" + 2
                callDialog(imageFileName)
            }
            viewHolder.imageView3_dtl.setOnClickListener {
                toast("image3 clicked")
                val imageFileName = timeFormat + "_" + region[p1].name2 + "_" + 3
                callDialog(imageFileName)
            }
            viewHolder.imageView4_dtl.setOnClickListener {
                toast("image4 clicked")
                val imageFileName = timeFormat + "_" + region[p1].name2 + "_" + 4
                callDialog(imageFileName)
            }
            viewHolder.imageView5_dtl.setOnClickListener {
                toast("image5 clicked")
                val imageFileName = timeFormat + "_" + region[p1].name2 + "_" + 5
                callDialog(imageFileName)
            }
            viewHolder.imageView6_dtl.setOnClickListener {
                toast("image6 clicked")
                val imageFileName = timeFormat + "_" + region[p1].name2 + "_" + 6
                callDialog(imageFileName)
            }
            viewHolder.imageView7_dtl.setOnClickListener {
                toast("image7 clicked")
                val imageFileName = timeFormat + "_" + region[p1].name2 + "_" + 7
                callDialog(imageFileName)
            }
            viewHolder.imageView8_dtl.setOnClickListener {
                toast("image8 clicked")
                val imageFileName = timeFormat + "_" + region[p1].name2 + "_" + 8
                callDialog(imageFileName)
            }
            viewHolder.imageView9_dtl.setOnClickListener {
                toast("image9 clicked")
                val imageFileName = timeFormat + "_" + region[p1].name2 + "_" + 9
                callDialog(imageFileName)
            }


        }

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(p0.context).inflate(R.layout.detail_trip_images, p0, false)
            return CustomViewHolder(view)
        }


        inner class CustomViewHolder : RecyclerView.ViewHolder {
            constructor(itemView: View) : super(itemView) {

            }
        }

        fun callDialog(imgName : String) {
            var imgUri : Uri? = null
            val storageRef = storage?.reference?.child(ID)!!.child(imgName)
            storageRef!!.downloadUrl.addOnCompleteListener { task ->
                imgUri = task.result
                val dialog = Dialog(this@DetailTripActivity)
                dialog.setContentView(R.layout.extend_image)
                Glide.with(this@DetailTripActivity).load(imgUri).into(dialog.et_imageView)
                dialog.show()

                dialog.et_imageView.setOnClickListener {
                    dialog.dismiss()
                }
            }


        }


    }


}
