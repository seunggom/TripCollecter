package com.seunggom.tripmanager

import android.app.DatePickerDialog
import android.content.Context
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
            var name1 : String? = null
            var name2 : String? = null
            var imageUrl: Set<String>? = null


            val builder = AlertDialog.Builder(this)
            val view = LayoutInflater.from(this).inflate(R.layout.edit_region, null, false)
            builder.setView(view)

            val sp1 = findViewById(R.id.spinner1) as Spinner
            val sp1Adapter = ArrayAdapter.createFromResource(this, R.array.si_do, R.layout.edit_region)
            sp1.adapter = sp1Adapter
            sp1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    name1 = sp1.selectedItem.toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            }

            val sp2 = findViewById(R.id.spinner2) as Spinner
            val sp2Adapter = ArrayAdapter.createFromResource(this, R.array.si_do, R.layout.edit_region)
            sp1.adapter = sp2Adapter
            sp1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    name2 = sp2.selectedItem.toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            }

            val dialog = builder.create()

            addFinishBtn.setOnClickListener {
                list.add(addRegionData(name1, name2))

                mAdapter.notifyItemInserted(0)

                dialog.dismiss()
            }

            dialog.show()
        }


    }





}
