package com.seunggom.tripmanager

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_setting.*


class SettingFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        auth = FirebaseAuth.getInstance()
        var mainActivity = activity as MainActivity
        mainActivity.progressBar.visibility = View.INVISIBLE



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingView_MyAccount.setOnClickListener {
            val dialog = AlertDialog.Builder(activity)
            val user = auth.currentUser
            var str = "로그인된 아이디는 " + user!!.email + " 입니다."
            dialog.setMessage(str)
            dialog.setPositiveButton("확인") {dialog, which ->
                dialog.dismiss()
            }
            dialog.show()
        }

        settingView_Logout.setOnClickListener {
            val dialog_bld = AlertDialog.Builder(activity)
            dialog_bld.setMessage("로그아웃 하시겠어요?").setPositiveButton("네") {dialog, which ->
                auth.signOut()
                if(auth.currentUser == null) {
                    var intent = Intent(activity, LoginActivity::class.java)
                    startActivity(intent)
                    var mainActivity = activity as MainActivity
                    mainActivity.finish()

                }
            }
            dialog_bld.setNegativeButton("아니오") { dialog, which ->
                dialog.cancel()
            }
            var alert = dialog_bld.create()
            alert.show()
        }
    }



}
