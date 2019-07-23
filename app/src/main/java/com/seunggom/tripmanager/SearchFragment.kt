package com.seunggom.tripmanager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*


class SearchFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        var mainActivity = activity as MainActivity
        mainActivity.progressBar.visibility = View.INVISIBLE

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
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
