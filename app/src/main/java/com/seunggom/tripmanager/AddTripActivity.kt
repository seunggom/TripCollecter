package com.seunggom.tripmanager

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.seunggom.tripmanager.model.ContentDTO
import com.seunggom.tripmanager.model.RegionDTO
import kotlinx.android.synthetic.main.activity_add_trip.*
import kotlinx.android.synthetic.main.edit_region.view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*



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

    var regionDTO = RegionDTO()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_trip)

        storage = FirebaseStorage.getInstance()
        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val doc = firestore!!.collection("regions").document(auth?.currentUser!!.email!!)
        doc.get().addOnSuccessListener { document ->
            if(document.exists())
                regionDTO = document.toObject(RegionDTO::class.java)
        }

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
                    var tmpImageText = arrayListOf<String>()
                    for (i in photoUri.iterator()) {
                        tmpUri.add(i)
                        tmpImageText.add("")
                    }
                    list.add(addRegionData(name1, name2, tmpUri, tmpImageText))
                    var tmpUriString = arrayListOf<String>()
                    for (i in photoUriString.iterator()) {
                        tmpUriString.add(i)
                    }
                    nameList.add(RegionNameAndPhoto(name1, name2, tmpUriString, tmpImageText)) /////////// 임시로
                    photoUri.removeAll(photoUri)
                    photoUriString.removeAll(photoUriString)
                    mAdapter.notifyDataSetChanged() }
                .setNeutralButton("취소", null)
                .create()

            //val view = LayoutInflater.from(this).inflate(R.layout.edit_region, null, false)
            val view = layoutInflater.inflate(R.layout.edit_region, null)

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
        val cntTime = System.currentTimeMillis()
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.KOREA).format(cntTime)

        var tmpsaveUri = ArrayList<Uri>()
        for (i in list.iterator()) {
            var imageNum = 1
            for (j in i.imageUri!!.iterator()) {
                val imageFileName = timeStamp + "_" + i.name2 + "_" + imageNum
                imageNum = imageNum + 1
                val storageRef = storage?.reference?.child(auth!!.currentUser!!.email!!)?.child(imageFileName)
                storageRef?.putFile(j)
            }
        }

        val contentDTO = ContentDTO()

        contentDTO.title = addTripName.text.toString()
        contentDTO.startDate = date1text.text.toString()
        contentDTO.endDate = date2text.text.toString()
        contentDTO.regionName = nameList
        //contentDTO.regionList = list
        contentDTO.rating = ratingBar.rating
        contentDTO.explain = explainText.text.toString()
        contentDTO.userId = auth?.currentUser?.email
        contentDTO.timestamp = cntTime
        if (radioGroup.checkedRadioButtonId == R.id.radioButton1) contentDTO.isOpen = true
        else if(radioGroup.checkedRadioButtonId == R.id.radioButton2) contentDTO.isOpen = false


        firestore?.collection("trips")?.document()?.set(contentDTO)



        for(i in nameList.iterator()) {
            var stringArray1 = resources.getStringArray(R.array.si_do)
            var stringArray2 : Array<String>? = null
            for(j in 0..stringArray1.size-1) {
                if(stringArray1[j] == i.name1) {
                    when (j) {
                        1 -> stringArray2 = resources.getStringArray(R.array.si_do_1)
                        2 -> stringArray2 = resources.getStringArray(R.array.si_do_2)
                        3 -> stringArray2 = resources.getStringArray(R.array.si_do_3)
                        4 -> stringArray2 = resources.getStringArray(R.array.si_do_4)
                        5 -> stringArray2 = resources.getStringArray(R.array.si_do_5)
                        6 -> stringArray2 = resources.getStringArray(R.array.si_do_6)
                        7 -> stringArray2 = resources.getStringArray(R.array.si_do_7)
                        8 -> stringArray2 = resources.getStringArray(R.array.si_do_8)
                        9 -> stringArray2 = resources.getStringArray(R.array.si_do_9)
                        10 -> stringArray2 = resources.getStringArray(R.array.si_do_10)
                    }

                    for(k in 0..stringArray2!!.size-1) {
                        if (stringArray2[k] == i.name2) {
                            when (j) {
                                1 -> regionDTO.si_do_1[k]++
                                2 -> regionDTO.si_do_2[k]++
                                3 -> regionDTO.si_do_3[k]++
                                4 -> regionDTO.si_do_4[k]++
                                5 -> regionDTO.si_do_5[k]++
                                6 -> regionDTO.si_do_6[k]++
                                7 -> regionDTO.si_do_7[k]++
                                8 -> regionDTO.si_do_8[k]++
                                9 -> regionDTO.si_do_9[k]++
                                10 -> regionDTO.si_do_10[k]++
                            }
                        }
                    }
                    break
                }

            }

        }
        firestore?.collection("regions")!!.document(auth?.currentUser!!.email!!).set(regionDTO)

        Toast.makeText(this, "업로드 성공", Toast.LENGTH_SHORT).show()
        progress_bar.visibility = View.GONE
        image_loaded = false
        mapimages.clear()



    }

}