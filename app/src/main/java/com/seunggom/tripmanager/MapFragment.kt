package com.seunggom.tripmanager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.seunggom.tripmanager.ZoomView


import kotlinx.android.synthetic.main.fragment_map.*


class MapFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_map, container, false)
    }


    /*
        // TODO: Rename method, update argument and hook method into UI event
        fun onButtonPressed(uri: Uri) {

        }

        override fun onAttach(context: Context) {
            super.onAttach(context)

        }

        override fun onDetach() {
            super.onDetach()
           // listener = null
        }

        /**
         * This interface must be implemented by activities that contain this
         * fragment to allow an interaction in this fragment to be communicated
         * to the activity and potentially other fragments contained in that
         * activity.
         *
         *
         * See the Android Training lesson [Communicating with Other Fragments]
         * (http://developer.android.com/training/basics/fragments/communicating.html)
         * for more information.
         */
        interface OnFragmentInteractionListener {
            // TODO: Update argument type and name
            fun onFragmentInteraction(uri: Uri)
        }

        */
    companion object {

        @JvmStatic
        fun newInstance() =
            MapFragment().apply {
                arguments = Bundle().apply {
                    // putString(ARG_PARAM1, param1)
                }
            }
    }
}
