package com.seunggom.tripmanager

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.seunggom.tripmanager.model.ContentDTO
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.search_result.view.*
import java.text.SimpleDateFormat
import java.util.*


class SearchFragment : Fragment() {
    var firestore: FirebaseFirestore? = null
    var mainView: View? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        firestore = FirebaseFirestore.getInstance()
        var mainActivity = activity as MainActivity
        mainActivity.progressBar.visibility = View.INVISIBLE

        // Inflate the layout for this fragment
        mainView = inflater.inflate(R.layout.fragment_search, container, false)
        return mainView
    }

    override fun onResume() {
        super.onResume()

        searchView.queryHint = "검색할 지역 이름을 입력하세요"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                // 검색버튼을 누른 경우
                Toast.makeText(activity, "검색을 시작합니다.", Toast.LENGTH_SHORT).show()
                var mainActivity = activity as MainActivity
                mainActivity.progressBar.visibility = View.VISIBLE
                searchRecyclerView.layoutManager = LinearLayoutManager(activity)
                searchRecyclerView.adapter = SearchAdapter(s)

                return true
            }

            override fun onQueryTextChange(s: String): Boolean {
                // 텍스트가 바뀔때마다 호출
                return true
            }
        })

    }


    inner class SearchAdapter(searchText : String) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        val contentDTOs: ArrayList<ContentDTO> = ArrayList()

        init {
            firestore?.collection("trips")?.orderBy("timestamp")!!.get().addOnSuccessListener { documents ->
                contentDTOs.clear()

                for (document in documents) {
                    if (document["open"] == true) {
                        var toCompare = document.toObject(ContentDTO::class.java)!!
                        for(i in toCompare.regionName!!.iterator()) {
                            if(i.name1 == searchText || i.name2 == searchText) {
                                contentDTOs.add(toCompare)
                                break
                            }
                        }
                    }
                }
                var mainActivity = activity as MainActivity
                mainActivity.progressBar.visibility = View.INVISIBLE
                notifyDataSetChanged()
            }
        }

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SearchViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.search_result, p0, false)
            return SearchViewHolder(view)
        }

        override fun getItemCount(): Int {
            var count = contentDTOs.size
            if(count==0) {
                search_result_info.text = "검색 결과가 없습니다."
                search_result_info.visibility = View.VISIBLE
            }
            else {
                search_result_info.text = count.toString() + "개의 기록이 검색되었습니다."
                search_result_info.visibility = View.VISIBLE
            }
            return contentDTOs.size
        }

        override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
            val viewHolder = (p0 as SearchViewHolder).itemView
            viewHolder.srText_title.text = contentDTOs[p1].title.toString()
            viewHolder.srText_uploadDate.text = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA).format(contentDTOs[p1].timestamp) + " uploaded"
            var regionNames : String = ""
            var iter = 1
            for (i in contentDTOs[p1].regionName!!.iterator()) {
                regionNames = regionNames + i.name1 + " " + i.name2
                if (iter != contentDTOs[p1].regionName!!.size) regionNames = regionNames + " + "
                iter++
            }
            viewHolder.srText_regions.text = regionNames
            viewHolder.sr_ratingBar.rating = contentDTOs[p1].rating

            viewHolder.setOnClickListener {
                var intent = Intent(activity, DetailTripActivity::class.java)
                intent.putExtra("trip_info", contentDTOs[p1])
                startActivity(intent)
            }
        }


        inner class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        }
    }



    companion object {

        @JvmStatic
        fun newInstance() =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    // putString(ARG_PARAM1, param1)
                }
            }
    }
}


// https://hamzzibari.tistory.com/110 - (Kotlin) SearchView를 이용한 RecyclerView filter 사용하기