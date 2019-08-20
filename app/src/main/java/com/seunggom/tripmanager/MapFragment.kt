package com.seunggom.tripmanager


import android.graphics.*
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
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

        var images = getResourceBitmap()
        var image_num = images.size

        var bitmap = makeBitmap(images[0], images[1])
        for(i in 2..image_num-1) {
            bitmap = makeBitmap(bitmap, images[i])
        }
        Glide.with(this).load(bitmap).into(map)
        Glide.with(this).load(R.drawable.map__outline).into(map_outline)

        /*
        Glide.with(this).load(R.drawable.map__outline).into(map_outline)

        /*
        특별시/광역시 -> a
        서울 1  인천2  대전3  세종4 광주5 울산6 부산7 대구8
        */
        Glide.with(this).load(R.drawable.map_0_seoul).into(map_a1)
        Glide.with(this).load(R.drawable.map_0_incheon).into(map_a2)
        Glide.with(this).load(R.drawable.map_0_daejeon).into(map_a3)
        Glide.with(this).load(R.drawable.map_0_sejong).into(map_a4)
        Glide.with(this).load(R.drawable.map_0_gwangju).into(map_a5)
        Glide.with(this).load(R.drawable.map_0_ulsan).into(map_a6)
        Glide.with(this).load(R.drawable.map_0_busan).into(map_a7)
        Glide.with(this).load(R.drawable.map_0_daegu).into(map_a8)

        /*
        경기도 b
        가평01 고양02 과천03 광명04 광주05 구리06 군포07 김포08 남양주09 동두천10 부천11 성남12 수원13 시흥14 안산15 안성16
        안양17 양주18 양평19 여주20 연천21 오산22 용인23 의왕24 의정부25 이천26 파주27 평택28 포천29 하남30 화성31

        */
        Glide.with(this).load(R.drawable.map_1_ansan).into(map_b15)
        Glide.with(this).load(R.drawable.map_1_anseong).into(map_b16)
        Glide.with(this).load(R.drawable.map_1_anyang).into(map_b17)
        Glide.with(this).load(R.drawable.map_1_bc).into(map_b11)
        Glide.with(this).load(R.drawable.map_1_dongdc).into(map_b10)
        Glide.with(this).load(R.drawable.map_1_ejb).into(map_b25)
        Glide.with(this).load(R.drawable.map_1_euiwang).into(map_b24)
        Glide.with(this).load(R.drawable.map_1_gc).into(map_b03)
        Glide.with(this).load(R.drawable.map_1_gimpo).into(map_b08)
        Glide.with(this).load(R.drawable.map_1_gj).into(map_b05)
        Glide.with(this).load(R.drawable.map_1_gm).into(map_b04)
        Glide.with(this).load(R.drawable.map_1_goyang).into(map_b02)
        Glide.with(this).load(R.drawable.map_1_gp).into(map_b01)
        Glide.with(this).load(R.drawable.map_1_gunpo).into(map_b07)
        Glide.with(this).load(R.drawable.map_1_guri).into(map_b06)
        Glide.with(this).load(R.drawable.map_1_hanam).into(map_b30)
        Glide.with(this).load(R.drawable.map_1_hs).into(map_b31)
        Glide.with(this).load(R.drawable.map_1_ic).into(map_b26)
        Glide.with(this).load(R.drawable.map_1_nyju).into(map_b09)
        Glide.with(this).load(R.drawable.map_1_os).into(map_b22)
        Glide.with(this).load(R.drawable.map_1_paju).into(map_b27)
        Glide.with(this).load(R.drawable.map_1_pocheon).into(map_b29)
        Glide.with(this).load(R.drawable.map_1_pt).into(map_b28)
        Glide.with(this).load(R.drawable.map_1_sh).into(map_b14)
        Glide.with(this).load(R.drawable.map_1_snm).into(map_b12)
        Glide.with(this).load(R.drawable.map_1_suwon).into(map_b13)
        Glide.with(this).load(R.drawable.map_1_yangju).into(map_b18)
        Glide.with(this).load(R.drawable.map_1_yeoju).into(map_b20)
        Glide.with(this).load(R.drawable.map_1_yeoncheon).into(map_b21)
        Glide.with(this).load(R.drawable.map_1_yi).into(map_b23)
        Glide.with(this).load(R.drawable.map_1_yp).into(map_b19)

        //Glide.with(this).load(R.drawable.tmp).into(map_tmp)
        // Glide.with(this).load(R.drawable.map_).into(map_)
        /*
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


        Glide.with(this).load(R.drawable.map_1_ansan).into(map_ansan)
        Glide.with(this).load(R.drawable.map_1_anseong).into(map_anseong)
        Glide.with(this).load(R.drawable.map_1_anyang).into(map_anyang)
        Glide.with(this).load(R.drawable.map_1_bc).into(map_bucheon)
        Glide.with(this).load(R.drawable.map_1_dongdc).into(map_dongducheon)
        Glide.with(this).load(R.drawable.map_1_ejb).into(map_euijeongbu)
        Glide.with(this).load(R.drawable.map_1_euiwang).into(map_euiwang)
        Glide.with(this).load(R.drawable.map_1_gc).into(map_gimcheon)
        Glide.with(this).load(R.drawable.map_1_gimpo).into(map_gimpo)
        Glide.with(this).load(R.drawable.map_1_gj).into(map_gwangju1)
        Glide.with(this).load(R.drawable.map_1_gm).into(map_gumi)
        Glide.with(this).load(R.drawable.map_1_goyang).into(map_goyang)
        Glide.with(this).load(R.drawable.map_1_gp).into(map_gapyeong)
        Glide.with(this).load(R.drawable.map_1_gunpo).into(map_gunpo)
        Glide.with(this).load(R.drawable.map_1_guri).into(map_guri)
        Glide.with(this).load(R.drawable.map_1_gwha).into(map_gangwha)
        Glide.with(this).load(R.drawable.map_1_hanam).into(map_hanam)
        Glide.with(this).load(R.drawable.map_1_hs).into(map_hwaseong)
        Glide.with(this).load(R.drawable.map_1_ic).into(map_icheon)
        Glide.with(this).load(R.drawable.map_1_nyju).into(map_namyangju)
        Glide.with(this).load(R.drawable.map_1_os).into(map_osan)
        Glide.with(this).load(R.drawable.map_1_paju).into(map_paju)
        Glide.with(this).load(R.drawable.map_1_pocheon).into(map_pocheon)
        Glide.with(this).load(R.drawable.map_1_pt).into(map_pyeongtaek)
        Glide.with(this).load(R.drawable.map_1_sh).into(map_siheung)
        Glide.with(this).load(R.drawable.map_1_snm).into(map_seongnam)
        Glide.with(this).load(R.drawable.map_1_suwon).into(map_suwon)
        Glide.with(this).load(R.drawable.map_1_yangju).into(map_yangju)
        Glide.with(this).load(R.drawable.map_1_yeoju).into(map_yeoju)
        Glide.with(this).load(R.drawable.map_1_yeoncheon).into(map_yeoncheon)
        Glide.with(this).load(R.drawable.map_1_yi).into(map_yongin)
        Glide.with(this).load(R.drawable.map_1_yp).into(map_yangpyeong)


        Glide.with(this).load(R.drawable.map_21_boeun).into(map_cb_boeun)
        Glide.with(this).load(R.drawable.map_21_cheongju).into(map_cb_cheongju)
        Glide.with(this).load(R.drawable.map_21_chungju).into(map_cb_chungju)
        Glide.with(this).load(R.drawable.map_21_danyang).into(map_cb_dy)
        Glide.with(this).load(R.drawable.map_21_eumseong).into(map_cb_es)
        Glide.with(this).load(R.drawable.map_21_goesan).into(map_cb_gs)
        Glide.with(this).load(R.drawable.map_21_jecheon).into(map_cb_jecheon)
        Glide.with(this).load(R.drawable.map_21_jeungpyeong).into(map_cb_jp)
        Glide.with(this).load(R.drawable.map_21_jincheon).into(map_cb_jincheon)
        Glide.with(this).load(R.drawable.map_21_okcheon).into(map_cb_ok)
        Glide.with(this).load(R.drawable.map_21_yeongdong).into(map_cb_yd)


        Glide.with(this).load(R.drawable.map_22_asan).into(map_cn_asan)
        Glide.with(this).load(R.drawable.map_22_boryeong).into(map_cn_bor)
        Glide.with(this).load(R.drawable.map_22_buyeo).into(map_cn_bu)
        Glide.with(this).load(R.drawable.map_22_cheonan).into(map_cn_cheonan)
        Glide.with(this).load(R.drawable.map_22_cheongyang).into(map_cn_cheongyang)
        Glide.with(this).load(R.drawable.map_22_dangjin).into(map_cn_dj)
        Glide.with(this).load(R.drawable.map_22_geumsan).into(map_cn_geum)
        Glide.with(this).load(R.drawable.map_22_gongju).into(map_cn_gongju)
        Glide.with(this).load(R.drawable.map_22_gyeryong).into(map_cn_gyeryong)
        Glide.with(this).load(R.drawable.map_22_hongseong).into(map_cn_hongseong)
        Glide.with(this).load(R.drawable.map_22_nonsan).into(map_cn_nonsan)
        Glide.with(this).load(R.drawable.map_22_seocheon).into(map_cn_seocheon)
        Glide.with(this).load(R.drawable.map_22_seosan).into(map_cn_seosan)
        Glide.with(this).load(R.drawable.map_22_taean).into(map_cn_taean)
        Glide.with(this).load(R.drawable.map_22_yesan).into(map_cn_yesan)


        Glide.with(this).load(R.drawable.map__outline).into(map_outline)
*/*/
        var mainActivity = activity as MainActivity
        mainActivity.progressBar.visibility = View.INVISIBLE

    }
//https://hoyytada.github.io/android/2017/12/10/dev-android-01.html

    fun getResourceBitmap() : ArrayList<Bitmap> {
        var returnArray = arrayListOf<Bitmap>()

        // 서울 1  인천2  대전3  세종4 광주5 울산6 부산7 대구8
        //var mDrawable = context!!.resources(R.drawable.map_0_seoul)
        //mDrawable.setColorFilter(Color.parseColor("#4CAF50"), PorterDuff.Mode.SRC_IN)
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_0_seoul))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_0_incheon))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_0_daejeon))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_0_sejong))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_0_gwangju))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_0_ulsan))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_0_busan))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_0_daegu))
        //가평01 고양02 과천03 광명04 광주05 구리06 군포07 김포08 남양주09 동두천10 부천11 성남12 수원13 시흥14 안산15 안성16
        //안양17 양주18 양평19 여주20 연천21 오산22 용인23 의왕24 의정부25 이천26 파주27 평택28 포천29 하남30 화성31
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_ansan))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_goyang))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_gc))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_gm))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_gj))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_guri))

        //returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_0_daegu))


        return returnArray
    }

    fun makeBitmap(original : Bitmap, target : Bitmap) : Bitmap {
        var resultBitmap = Bitmap.createBitmap(original.width, original.height, original.config)

        var color_paint = Paint()
        color_paint.setColorFilter(PorterDuffColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY))

        var canvas = Canvas(resultBitmap)
        canvas.drawBitmap(original, Matrix(), null)
        canvas.drawBitmap(target, Matrix(), color_paint)

        return resultBitmap
    }


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
