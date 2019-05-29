package com.seunggom.tripmanager

import android.app.DatePickerDialog
import android.content.Context
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


class AddTripActivity : AppCompatActivity() {
    var title: String? = null


    var list = arrayListOf<addRegionData>(
        addRegionData("전라남도 순천")
    )


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
            val builder = AlertDialog.Builder(this)
            val view = LayoutInflater.from(this).inflate(R.layout.edit_region, null, false)
            builder.setView(view)

            val spinner1_list = arrayOf("특별/광역시", "경기도", "강원도", "충청북도", "충청남도",
                "경상북도", "경상남도", "전라북도", "전라남도", "제주특별자치시")

//            spinner1.adapter = ArrayAdapter(this, R.layout.edit_region, spinner1_list)

            builder.show()

        }

    }



/*
    fun showDatePicker(item: TextView) {

        if (item == startDate) {
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                start_cal.set(Calendar.YEAR, year)
                start_cal.set(Calendar.MONTH, month)
                start_cal.set(Calendar.DAY_OF_MONTH, day)
            }, start_cal.get(Calendar.YEAR), start_cal.get(Calendar.MONTH), start_cal.get(Calendar.DAY_OF_MONTH)).show()
        }
        else if(item == endDate) {
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                end_cal.set(Calendar.YEAR, year)
                end_cal.set(Calendar.MONTH, month)
                end_cal.set(Calendar.DAY_OF_MONTH, day)
            }, end_cal.get(Calendar.YEAR), end_cal.get(Calendar.MONTH), end_cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }
    */

}
