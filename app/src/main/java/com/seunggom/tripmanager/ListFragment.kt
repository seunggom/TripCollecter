package com.seunggom.tripmanager


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.seunggom.tripmanager.model.ContentDTO
import com.squareup.okhttp.OkHttpClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.list_trip.view.*
import java.util.*





class ListFragment : Fragment() {
    private var auth: FirebaseAuth? = null
    var firestore: FirebaseFirestore? = null
    var imagesSnapshot: ListenerRegistration? = null
    var mainView: View? = null
    var okHttpClient: OkHttpClient? = null




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

        mainView?.ListRecyclerView?.layoutManager = LinearLayoutManager(activity)
        mainView?.ListRecyclerView?.adapter = RecyclerViewAdapter()
        //var mainActivity = activity as MainActivity
        //mainActivity.progressBar.visibility = View.INVISIBLE

    }



    override fun onStop() {
        super.onStop()
        imagesSnapshot?.remove()
    }

    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        val contentDTOs: ArrayList<ContentDTO>
        val contentUidList: ArrayList<String>

        init {
            contentDTOs = ArrayList()
            contentUidList = ArrayList()

            val userEmail = auth!!.currentUser?.email
            firestore?.collection("trips")?.orderBy("timestamp")!!.get().addOnSuccessListener { documents ->
                contentDTOs.clear()
                contentUidList.clear()

                for (document in documents) {
                    if (document["userId"] == userEmail) {
                        var item = document.toObject(ContentDTO::class.java)!!
                        contentDTOs.add(item)
                    }
                }
                var mainActivity = activity as MainActivity
                mainActivity.progressBar.visibility = View.INVISIBLE
                notifyDataSetChanged()
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
                regionNames = regionNames + i.name1 + " " + i.name2
                if (iter != contentDTOs[position].regionName!!.size) regionNames = regionNames + " / "
                iter++
            }
            viewHolder.region.text = regionNames

            viewHolder.button2.setOnClickListener {

                var intent = Intent(activity, DetailTripActivity::class.java)
                intent.putExtra("trip_info", contentDTOs[position])
                startActivity(intent)
            }

        }


        inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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