package com.seunggom.tripmanager

import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_add_trip.*
import org.jetbrains.anko.toast
import java.util.*
import android.view.View
import android.widget.DatePicker
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.AdapterView
import kotlinx.android.synthetic.main.edit_region.*
import kotlinx.android.synthetic.main.edit_region.view.*


class AddTripActivity : AppCompatActivity() {
    var title: String? = null


    var list = arrayListOf<addRegionData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_trip)

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
            var imageUrl: Set<String>? = null


            val builder = AlertDialog.Builder(this)

            builder.setTitle("지역 & 사진 추가하기")
                .setPositiveButton("추가") {dialog, which ->
                    list.add(addRegionData(name1, name2))
                    mAdapter.notifyItemInserted(0) }
                .setNeutralButton("취소", null)
                .create()

            val view = LayoutInflater.from(this).inflate(R.layout.edit_region, null, false)
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

            builder.show()
        }


    }





}
