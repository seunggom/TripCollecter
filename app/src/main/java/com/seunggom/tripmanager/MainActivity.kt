package com.seunggom.tripmanager

import android.Manifest
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {



    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_map -> {
                progressBar.visibility = View.VISIBLE
                replaceFragment(MapFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_list -> {
                progressBar.visibility = View.VISIBLE
                replaceFragment(ListFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                progressBar.visibility = View.VISIBLE
                replaceFragment(SearchFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //toast(getString(R.string.signin_complete))
        progressBar.visibility = View.VISIBLE
        if(savedInstanceState == null) { // 첫 화면은 MapFragment
            replaceFragment(MapFragment())
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        //progress
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.addtrip, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId)
        {
            R.id.add_btn -> {
                startActivity<AddTripActivity>()
                toast("새로운 여행 기록을 추가합니다")
            }

        }
        return super.onOptionsItemSelected(item)
    }
}
