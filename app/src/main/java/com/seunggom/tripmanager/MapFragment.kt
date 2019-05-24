package com.seunggom.tripmanager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide


import kotlinx.android.synthetic.main.fragment_map.*


class MapFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        image_glide()
    }

    fun image_glide() {
        // Glide.with(this).load(R.drawable.map_).into(map_)

        Glide.with(this).load(R.drawable.map_0_gwangju).into(map_gwangju)

        Glide.with(this).load(R.drawable.map_9_jeju1).into(map_jeju1)
        Glide.with(this).load(R.drawable.map_9_jeju2).into(map_jeju2)

        Glide.with(this).load(R.drawable.map_32_boseong).into(map_jn_boseong)
        Glide.with(this).load(R.drawable.map_32_damyang).into(map_jn_damyang)
        Glide.with(this).load(R.drawable.map_32_gangjin).into(map_jn_gangjin)
        Glide.with(this).load(R.drawable.map_32_goheung).into(map_jn_goheung)
        Glide.with(this).load(R.drawable.map_32_gokseong).into(map_jn_gokseong)
        Glide.with(this).load(R.drawable.map_32_gurye).into(map_jn_gurye)
        Glide.with(this).load(R.drawable.map_32_gwangyang).into(map_jn_gwangyang)
        Glide.with(this).load(R.drawable.map_32_haenam).into(map_jn_haenam)
        Glide.with(this).load(R.drawable.map_32_hampyeong).into(map_jn_hampyeong)
        Glide.with(this).load(R.drawable.map_32_hwasun).into(map_jn_hwasun)
        Glide.with(this).load(R.drawable.map_32_jangheung).into(map_jn_jangheung)
        Glide.with(this).load(R.drawable.map_32_jangseong).into(map_jn_jangseong)
        Glide.with(this).load(R.drawable.map_32_jindo).into(map_jn_jindo)
        Glide.with(this).load(R.drawable.map_32_mokpo).into(map_jn_mokpo)
        Glide.with(this).load(R.drawable.map_32_muan).into(map_jn_muan)
        Glide.with(this).load(R.drawable.map_32_naju).into(map_jn_naju)
        Glide.with(this).load(R.drawable.map_32_sinan).into(map_jn_sinan)
        Glide.with(this).load(R.drawable.map_32_suncheon).into(map_jn_suncheon)
        Glide.with(this).load(R.drawable.map_32_wando).into(map_jn_wando)
        Glide.with(this).load(R.drawable.map_32_yeongam).into(map_jn_yeongam)
        Glide.with(this).load(R.drawable.map_32_yeonggwang).into(map_jn_yeonggwang)
        Glide.with(this).load(R.drawable.map_32_yeosu).into(map_jn_yeosu)

        Glide.with(this).load(R.drawable.map_31_buan).into(map_jb_buan)
        Glide.with(this).load(R.drawable.map_31_gimje).into(map_jb_gimje)
        Glide.with(this).load(R.drawable.map_31_gochang).into(map_jb_gochang)
        Glide.with(this).load(R.drawable.map_31_gunsan).into(map_jb_gunsan)
        Glide.with(this).load(R.drawable.map_31_iksan).into(map_jb_iksan)
        Glide.with(this).load(R.drawable.map_31_imsil).into(map_jb_imsil)
        Glide.with(this).load(R.drawable.map_31_jangsu).into(map_jb_jangsu)
        Glide.with(this).load(R.drawable.map_31_jeongeup).into(map_jb_jeongeup)
        Glide.with(this).load(R.drawable.map_31_jeonju).into(map_jb_jeonju)
        Glide.with(this).load(R.drawable.map_31_jinan).into(map_jb_jinan)
        Glide.with(this).load(R.drawable.map_31_muju).into(map_jb_muju)
        Glide.with(this).load(R.drawable.map_31_namwon).into(map_jb_namwon)
        Glide.with(this).load(R.drawable.map_31_sunchang).into(map_jb_sunchang)
        Glide.with(this).load(R.drawable.map_31_wanju).into(map_jb_wanju)

        /*
        Glide.with(this).load(R.drawable.map_gb_andong).into(map_gb_andong)
        Glide.with(this).load(R.drawable.map_gb_bonghwa).into(map_gb_bonghwa)
        Glide.with(this).load(R.drawable.map_gb_cheongdo).into(map_gb_cheongdo)
        Glide.with(this).load(R.drawable.map_gb_cheongsong).into(map_gb_cheongsong)
        Glide.with(this).load(R.drawable.map_gb_cilgok).into(map_gb_cilgok)
        Glide.with(this).load(R.drawable.map_gb_gimcheon).into(map_gb_gimcheon)
        Glide.with(this).load(R.drawable.map_gb_goryeong).into(map_gb_goryeong)
        Glide.with(this).load(R.drawable.map_gb_gumi).into(map_gb_gumi)
        Glide.with(this).load(R.drawable.map_gb_gunwi).into(map_gb_gunwi)
        Glide.with(this).load(R.drawable.map_gb_gyeongju).into(map_gb_gyeongju)
        Glide.with(this).load(R.drawable.map_gb_gyeongsan).into(map_gb_gyeongsan)
        Glide.with(this).load(R.drawable.map_gb_mungyeong).into(map_gb_mungyeong)
        Glide.with(this).load(R.drawable.map_gb_pohang).into(map_gb_pohang)
        Glide.with(this).load(R.drawable.map_gb_sangju).into(map_gb_sangju)
        Glide.with(this).load(R.drawable.map_gb_seongju).into(map_gb_seongju)
        Glide.with(this).load(R.drawable.map_gb_uljin).into(map_gb_uljin)
        Glide.with(this).load(R.drawable.map_gb_ulreung).into(map_gb_ulreung)
        Glide.with(this).load(R.drawable.map_gb_yecheon).into(map_gb_yecheon)
        Glide.with(this).load(R.drawable.map_gb_yeongcheon).into(map_gb_yeongcheon)
        Glide.with(this).load(R.drawable.map_gb_yeongdeok).into(map_gb_yeongdeok)
        Glide.with(this).load(R.drawable.map_gb_yeongju).into(map_gb_yeongju)
        Glide.with(this).load(R.drawable.map_gb_yeongyang).into(map_gb_yeongyang)
        Glide.with(this).load(R.drawable.map_gb_yuiseong).into(map_gb_yuiseong)
*/
        Glide.with(this).load(R.drawable.map_0_busan).into(map_busan)
        Glide.with(this).load(R.drawable.map_0_daegu).into(map_daegu)
        Glide.with(this).load(R.drawable.map_0_daejeon).into(map_daejeon)
        Glide.with(this).load(R.drawable.map_0_sejong).into(map_sejong)
        Glide.with(this).load(R.drawable.map_0_ulsan).into(map_ulsan)



        Glide.with(this).load(R.drawable.map__outline).into(map_outline)

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
