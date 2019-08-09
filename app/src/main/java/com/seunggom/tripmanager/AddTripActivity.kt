package com.seunggom.tripmanager

import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_add_trip.*
import java.util.*
import android.view.View
import kotlinx.android.synthetic.main.edit_region.*
import kotlinx.android.synthetic.main.edit_region.view.*
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v4.app.ActivityCompat
import android.widget.*
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.seunggom.tripmanager.model.ContentDTO
import org.jetbrains.anko.*
import java.text.SimpleDateFormat
import android.content.ClipData



class AddTripActivity : AppCompatActivity() {
    val PICK_IMAGE_FROM_ALBUM = 0
    var title: String? = null
    var photoUri = arrayListOf<Uri>()

    var photoUriString = arrayListOf<String>()
    var list = arrayListOf<addRegionData>()
    var nameList = arrayListOf<RegionNameAndPhoto>()

    var storage: FirebaseStorage? = null
    var firestore: FirebaseFirestore? = null
    private var auth: FirebaseAuth? = null

    var resultOK : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_trip)

        storage = FirebaseStorage.getInstance()
        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val mAdapter = AddRegionViewAdapter(this, list)
        mRecyclerView.adapter = mAdapter

        val lm = LinearLayoutManager(this)
        mRecyclerView.layoutManager = lm
        mRecyclerView.setHasFixedSize(true)


        date1button.setOnClickListener { view ->
            var calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)

            var date_listener  = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    date1text.text = "${year}.${month + 1}.${dayOfMonth}"
                }
            }
            var builder = DatePickerDialog(this, date_listener, year, month, day)
            builder.show()
        }

        date2button.setOnClickListener { view ->
            var calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)

            var date_listener  = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    date2text.text = "${year}.${month + 1}.${dayOfMonth}"
                }
            }

            var builder = DatePickerDialog(this, date_listener, year, month, day)
            builder.show()

        }

        addRegionBtn.setOnClickListener {
            var name1 : String? = null
            var si_do_pos : Int = 0
            var name2 : String? = null


            val builder = AlertDialog.Builder(this)

            builder.setTitle("지역 & 사진 추가하기")
                .setPositiveButton("추가") {dialog, which ->
                    var tmpUri = arrayListOf<Uri>()
                    for (i in photoUri.iterator()) {
                        tmpUri.add(i)
                    }
                    list.add(addRegionData(name1, name2, tmpUri))
                    var tmpUriString = arrayListOf<String>()
                    for (i in photoUriString.iterator()) {
                        tmpUriString.add(i)
                    }
                    nameList.add(RegionNameAndPhoto(name1, name2, tmpUriString)) /////////// 임시로
                    mAdapter.notifyDataSetChanged() }
                .setNeutralButton("취소", null)
                .create()

            //val view = LayoutInflater.from(this).inflate(R.layout.edit_region, null, false)
            val view = layoutInflater.inflate(R.layout.edit_region, null)
            //Glide.with(view).load(R.drawable.abc_ic_star_black_48dp).into(view.imageView1)
            builder.setView(view)

            val sp1Adapter = ArrayAdapter.createFromResource(this, R.array.si_do, android.R.layout.simple_spinner_dropdown_item)
            view.spinner1.adapter = sp1Adapter
            val sp2Adapter = ArrayAdapter.createFromResource(this, R.array.si_do_0, android.R.layout.simple_spinner_dropdown_item)
            view.spinner2.adapter = sp2Adapter

            view.spinner1.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
                override fun onItemSelected(parent: AdapterView<*>, v: View?, position: Int, id: Long) {
                    name1 = view.spinner1.getItemAtPosition(position).toString()

                    when(position) {
                        0 -> si_do_pos = R.array.si_do_0
                        1 -> si_do_pos = R.array.si_do_1
                        2 -> si_do_pos = R.array.si_do_2
                        3 -> si_do_pos = R.array.si_do_3
                        4 -> si_do_pos = R.array.si_do_4
                        5 -> si_do_pos = R.array.si_do_5
                        6 -> si_do_pos = R.array.si_do_6
                        7 -> si_do_pos = R.array.si_do_7
                        8 -> si_do_pos = R.array.si_do_8
                        9 -> si_do_pos = R.array.si_do_9
                        10 -> si_do_pos = R.array.si_do_10
                    }

                    view.spinner2.adapter = ArrayAdapter.createFromResource(this@AddTripActivity, si_do_pos, android.R.layout.simple_spinner_dropdown_item)

                }
            }

            view.spinner2.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(parent: AdapterView<*>, v: View?, position: Int, id: Long) {
                    name2 = view.spinner2.getItemAtPosition(position).toString()
                }
            }

            view.photoAddBtn.setOnClickListener {

                val photoPickerIntent = Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI)
                photoPickerIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                photoPickerIntent.type = "image/*"
                startActivityForResult(Intent.createChooser(photoPickerIntent, "사진을 선택하세요."), PICK_IMAGE_FROM_ALBUM)
            }

            builder.show()
        }


        addDataButton.setOnClickListener {
            contentUpload()
            startActivity<MainActivity>()
            finish()
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == PICK_IMAGE_FROM_ALBUM) {
            if(resultCode == Activity.RESULT_OK) {

                photoUri.removeAll(photoUri)
                photoUriString.removeAll(photoUriString)

                var imgUri = arrayListOf<Uri>()
                var imgUriString = arrayListOf<String>()

                val view = layoutInflater.inflate(R.layout.edit_region, null)

                val Uri = data!!.data
                val clipData = data!!.clipData


                if (clipData != null) {
                    for (i in 0..8) {
                        if (i < clipData.getItemCount()) {
                            val urione = clipData.getItemAt(i).uri
                            imgUri.add(urione)
                            imgUriString.add(urione.toString())

                        }
                    }

                }

                toast("이미지 선택 완료")
                photoUri = imgUri
                photoUriString = imgUriString
            }
        }
    }


    fun contentUpload() {
        progress_bar.visibility = View.VISIBLE
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.KOREA).format(Date())
        for (i in list.iterator()) {
            var imageNum = 1
            for (j in i.imageUri!!.iterator()) {
                val imageFileName = "JPEG_" + timeStamp + "_" + i.name1 + "_" + i.name2 + "_" + imageNum +"_.png"
                imageNum = imageNum + 1
                val storageRef = storage?.reference?.child("images")?.child(imageFileName)
                storageRef?.putFile(j)
            }
        }
        //val imageFileName =
        //val uri = taskSnapshot.downloadUrl
        val contentDTO = ContentDTO()

        contentDTO.title = addTripName.text.toString()
        contentDTO.startDate = date1text.text.toString()
        contentDTO.endDate = date2text.text.toString()
        contentDTO.regionName = nameList
        //contentDTO.regionList = list
        contentDTO.rating = ratingBar.rating
        contentDTO.explain = explainText.text.toString()
        contentDTO.userId = auth?.currentUser?.email
        contentDTO.timestamp = System.currentTimeMillis()

        firestore?.collection("trips")?.document()?.set(contentDTO)
        Toast.makeText(this, "업로드 성공", Toast.LENGTH_SHORT).show()
        progress_bar.visibility = View.GONE


    }

}
