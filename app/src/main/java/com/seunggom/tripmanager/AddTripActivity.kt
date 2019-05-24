package com.seunggom.tripmanager

import android.app.DatePickerDialog
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_add_trip.*
import org.jetbrains.anko.toast
import java.util.*
import android.view.View

class AddTripActivity : AppCompatActivity() {

    var start_cal = Calendar.getInstance()
    val s_year = start_cal.get(Calendar.YEAR)
    val s_month = start_cal.get(Calendar.MONTH)
    val s_day = start_cal.get(Calendar.DAY_OF_MONTH)
    var end_cal = Calendar.getInstance()
    val e_year = end_cal.get(Calendar.YEAR)
    val e_month = end_cal.get(Calendar.MONTH)
    val e_day = end_cal.get(Calendar.DAY_OF_MONTH)

    inner class AddRegionViewAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun getItemCount(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {

        }

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.add_region, p0, false)

            return CustomViewHolder(view)
        }

        inner class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_trip)

        val mAdapter = AddRegionViewAdapter(this)
        mRecyclerView.adapter = mAdapter
/*
        startDate.setOnClickListener {
            showDatePicker(startDate)
            startDate.setText("${start_cal.get(Calendar.YEAR)}/${start_cal.get(Calendar.MONTH)+1}/${start_cal.get(Calendar.DAY_OF_MONTH)}")
        }
        endDate.setOnClickListener {
            showDatePicker(endDate)
            endDate.setText("${end_cal.get(Calendar.YEAR)}/${end_cal.get(Calendar.MONTH)+1}/${end_cal.get(Calendar.DAY_OF_MONTH)}")
        }
        */
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
