package com.seunggom.tripmanager


import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.seunggom.tripmanager.model.ContentDTO
import com.seunggom.tripmanager.model.RegionDTO
import com.squareup.okhttp.OkHttpClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.list_trip.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ListFragment : Fragment() {
    private var auth: FirebaseAuth? = null
    var firestore: FirebaseFirestore? = null
    var mainView: View? = null
    var okHttpClient: OkHttpClient? = null
    val storage = FirebaseStorage.getInstance("gs://tripcollecter-6499f.appspot.com")
    var filter_region : Int = 0
    var filter_sort : Int = 0 // 0이면 업로드 순으로, 1이면 여행 날짜 순으로 정렬
    var all_contentDTOs: ArrayList<ContentDTO> = ArrayList()
    var loading_finish = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        mainView = inflater.inflate(R.layout.fragment_list, container, false)


        return mainView
    }

    override fun onResume() {
        super.onResume()

        mainView?.list_spinner!!.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                filter_region = position
            }
        }

        mainView?.ListRecyclerView?.layoutManager =  LinearLayoutManager(activity)
        mainView?.ListRecyclerView?.adapter = RecyclerViewAdapter()
        //var mainActivity = activity as MainActivity
        //mainActivity.progressBar.visibility = View.INVISIBLE

        list_set_button.setOnClickListener {
            if(loading_finish) {
                if(mainView?.radioGroup2!!.checkedRadioButtonId == R.id.radioButton_startdate) filter_sort = 1
                else filter_sort = 0
                (mainView?.ListRecyclerView?.adapter as RecyclerViewAdapter).filter(filter_region, filter_sort)
                Toast.makeText(activity, "필터를 적용합니다.", Toast.LENGTH_SHORT).show()
            }
        }

    }


    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        val contentDTOs: ArrayList<ContentDTO>

        init {
            contentDTOs = ArrayList()

            val userEmail = auth!!.currentUser?.email
            firestore?.collection("trips")?.orderBy("timestamp")!!.get().addOnSuccessListener { documents ->
                contentDTOs.clear()
                all_contentDTOs.clear()

                for (document in documents) {
                    if (document["userId"] == userEmail) {
                        var item = document.toObject(ContentDTO::class.java)!!
                        contentDTOs.add(item)
                        all_contentDTOs.add(item)
                    }
                }
                var mainActivity = activity as MainActivity
                mainActivity.progressBar.visibility = View.INVISIBLE
                notifyDataSetChanged()
                loading_finish = true
            }
        }


        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.list_trip, p0, false)
            return CustomViewHolder(view)
        }

        override fun getItemCount(): Int {
            return contentDTOs.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val viewHolder = (holder as CustomViewHolder).itemView
            viewHolder.tripTitle.text = contentDTOs[position].title.toString()
            viewHolder.startDate.text = contentDTOs[position].startDate.toString()
            viewHolder.endDate.text = contentDTOs[position].endDate.toString()
            var regionNames : String = ""
            var iter = 1
            for (i in contentDTOs[position].regionName!!.iterator()) {
                if(i.name1 == "직접입력") regionNames = regionNames + i.name2
                else regionNames = regionNames + i.name1 + " " + i.name2
                if (iter != contentDTOs[position].regionName!!.size) regionNames = regionNames + " + "
                iter++
            }
            viewHolder.region.text = regionNames

            viewHolder.button2.setOnClickListener {

                var intent = Intent(activity, DetailTripActivity::class.java)
                intent.putExtra("trip_info", contentDTOs[position])
                startActivity(intent)
            }

        }


        inner class CustomViewHolder : RecyclerView.ViewHolder {
            constructor(itemView: View) : super(itemView) {
                itemView.setOnLongClickListener {
                    var pos = adapterPosition
                    if(pos != RecyclerView.NO_POSITION) {
                        callDialog_for_delete_log(contentDTOs[pos], pos)
                    }
                    return@setOnLongClickListener true
                }

            }
        }

        fun callDialog_for_delete_log(content : ContentDTO, index: Int) {
            val delete_dialog = AlertDialog.Builder(activity)
            delete_dialog.setMessage("'" + content.title + "' 여행 기록을 삭제할까요?").setCancelable(false)
            delete_dialog.setTitle("여행 기록 삭제하기")
            delete_dialog.setPositiveButton("네", DialogInterface.OnClickListener() {
                    dialogInterface: DialogInterface, i: Int ->
                contentDTOs.removeAt(index)
                var regions1_to_remove : ArrayList<String> = ArrayList()
                var regions2_to_remove : ArrayList<Int> = ArrayList()
                firestore!!.collection("trips").whereEqualTo("userId", auth?.currentUser!!.email!!).get().addOnSuccessListener {
                    document ->
                    for (doc in document)
                        // 여행기록의 제목과 업로드 날짜가 같으면 삭제
                        if(doc["title"] == content.title && doc["timestamp"] == content.timestamp) {
                            // 삭제할 기록에 속한 지역들 알아내기
                            var regionDTO = RegionDTO()
                            firestore!!.collection("regions").document(auth?.currentUser!!.email!!).get().addOnCompleteListener { docc ->
                                if (docc.isSuccessful) {
                                    regionDTO = docc.result.toObject(RegionDTO::class.java)
                                    for (i in content.regionName!!.iterator()) {
                                        var stringArray1 = resources.getStringArray(R.array.si_do)
                                        var stringArray2: Array<String>? = null
                                        for (j in 0..stringArray1.size - 1) {
                                            if (stringArray1[j] == i.name1) {
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

                                                for (k in 0..stringArray2!!.size - 1) {
                                                    if (stringArray2[k] == i.name2) {
                                                        when (j) {
                                                            1 -> regionDTO.si_do_1[k]--
                                                            2 -> regionDTO.si_do_2[k]--
                                                            3 -> regionDTO.si_do_3[k]--
                                                            4 -> regionDTO.si_do_4[k]--
                                                            5 -> regionDTO.si_do_5[k]--
                                                            6 -> regionDTO.si_do_6[k]--
                                                            7 -> regionDTO.si_do_7[k]--
                                                            8 -> regionDTO.si_do_8[k]--
                                                            9 -> regionDTO.si_do_9[k]--
                                                            10 -> regionDTO.si_do_10[k]--
                                                        }
                                                    }
                                                }
                                                break
                                            }
                                        }
                                        // 지역 카운팅 변경 완료
                                    }
                                }
                                // 지역 카운팅 변경 한걸 새로 업데이트
                                firestore?.collection("regions")!!.document(auth?.currentUser!!.email!!).set(regionDTO)
                            }


                            // document 삭제
                            firestore!!.collection("trips").document(doc.id).delete()

                            // storage에 있는 사진들 삭제
                            var timeFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.KOREA).format(content.timestamp)
                            for(i in content.regionName!!.iterator()) {
                                val count = i.photo!!.size
                                var imageNum = 1
                                for(j in 1..count) {
                                    var imageName = timeFormat + "_" + i.name2 + "_" + imageNum
                                    imageNum++
                                    storage.reference.child(auth?.currentUser!!.email!!).child(imageName).delete()
                                }
                            }
                            Toast.makeText(activity, "삭제되었습니다.", Toast.LENGTH_SHORT).show()
                            break
                        }


                    }


                notifyItemRemoved(index)
                notifyItemRangeChanged(index, contentDTOs.size)
            })
            delete_dialog.setNegativeButton("아니오", DialogInterface.OnClickListener { dialog, which ->

            })
            delete_dialog.show()

        }

        fun filter(region:Int, sort:Int) {
            contentDTOs.clear()

            for(i in all_contentDTOs.iterator()) {
                if(region == 0) contentDTOs.add(i)
                else {
                    for(j in i.regionName!!.iterator()) {
                        if(region == 1 && j.name1 == "특별/광역시") {
                            contentDTOs.add(i); break
                        }
                        else if(region == 2 && j.name1 == "경기도") {
                            contentDTOs.add(i); break
                        }
                        else if(region == 3 && j.name1 == "강원도") {
                            contentDTOs.add(i); break
                        }
                        else if(region == 4 && j.name1 == "충청북도") {
                            contentDTOs.add(i); break
                        }
                        else if(region == 5 && j.name1 == "충청남도") {
                            contentDTOs.add(i); break
                        }
                        else if(region == 6 && j.name1 == "경상북도") {
                            contentDTOs.add(i); break
                        }
                        else if(region == 7 && j.name1 == "경상남도") {
                            contentDTOs.add(i); break
                        }
                        else if(region == 8 && j.name1 == "전라북도") {
                            contentDTOs.add(i); break
                        }
                        else if(region == 9 && j.name1 == "전라남도") {
                            contentDTOs.add(i); break
                        }
                        else if(region == 10 && j.name1 == "제주특별자치시") {
                            contentDTOs.add(i); break
                        }
                    }
                }
            }

            var sorting1 = Comparator<ContentDTO> { c1: ContentDTO, c2: ContentDTO ->
                return@Comparator c1.startDate!!.compareTo(c2.startDate!!)
            }

            if(sort == 1) Collections.sort(contentDTOs, sorting1)

            notifyDataSetChanged()
        }

    }



    companion object {

        @JvmStatic
        fun newInstance() =
            ListFragment().apply {
                arguments = Bundle().apply {
                    // putString(ARG_PARAM1, param1)
                }
            }
    }

}



// https://recipes4dev.tistory.com/168