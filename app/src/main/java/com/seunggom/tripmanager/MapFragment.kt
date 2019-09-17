package com.seunggom.tripmanager


import android.graphics.*
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.seunggom.tripmanager.model.RegionDTO
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_map.*


class MapFragment : Fragment() {

    var firestore: FirebaseFirestore? = null
    private var auth: FirebaseAuth? = null
    var regionDTO : RegionDTO? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val docRef = firestore!!.collection("regions").document(auth?.currentUser!!.email!!)
        docRef.get().addOnSuccessListener {document ->
            if(document.exists())
                regionDTO = document.toObject(RegionDTO::class.java)
            else {
                var new_regionDTO = RegionDTO()
                firestore?.collection("regions")?.document(auth?.currentUser!!.email!!)?.set(new_regionDTO)
                regionDTO = new_regionDTO
            }
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        image_glide()

        //progressBar2.visibility = View.GONE
        var mainActivity = activity as MainActivity
        mainActivity.progressBar.visibility = View.INVISIBLE

    }


    //https://forums.developer.amazon.com/questions/12734/illegalargumentexception-on-lockcanvas.html

    fun image_glide() {
        //mainActivity.progressBar.visibility = View.VISIBLE

        var images = getResourceBitmap()
        var image_num = images.size

        var bitmap = makeBitmap(images[0], images[1])
        for(i in 2..image_num-1) {
            bitmap = makeBitmap(bitmap, images[i])
        }
        Glide.with(this).load(bitmap).into(map_1)
        Glide.with(this).load(R.drawable.map__outline).into(map_outline)

        //mainActivity.progressBar.visibility = View.GONE

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
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_gp))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_goyang))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_gc))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_gm))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_gj))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_guri))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_gunpo))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_gimpo))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_nyju))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_dongdc))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_bc))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_snm))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_suwon))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_sh))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_ansan))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_anseong))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_anyang))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_yangju))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_yp))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_yeoju))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_yeoncheon))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_os))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_yi))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_euiwang))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_ejb))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_ic))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_paju))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_pt))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_pocheon))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_hanam))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_1_hs))

        // 강원도
        // 강릉 고성 동해 삼척 속초 양구 양양 영월 원주 인제 정선 철원 춘천 태백 평창 홍천 화천 횡성
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_2_gr))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_2_gs))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_2_dh))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_2_sc))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_2_sokcho))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_2_yg))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_2_yy))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_2_yw))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_2_wj))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_2_ij))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_2_js))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_2_cw))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_2_cc))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_2_tb))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_2_pc))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_2_hc))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_2_hwacheon))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_2_hs))

        // 괴산 단양 보은 영동 옥천 음성 제천 증평 진천 청주 충주
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_21_goesan))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_21_danyang))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_21_boeun))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_21_yeongdong))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_21_okcheon))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_21_eumseong))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_21_jecheon))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_21_jeungpyeong))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_21_jincheon))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_21_cheongju))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_21_chungju))

        // 계룡 공주 금산 논산 당진 보령 부여 서산 서천 아산 예산 천안 청양 태안 홍성
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_22_gyeryong))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_22_gongju))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_22_geumsan))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_22_nonsan))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_22_dangjin))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_22_boryeong))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_22_buyeo))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_22_seosan))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_22_asan))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_22_yesan))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_22_cheonan))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_22_cheongyang))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_22_taean))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_22_hongseong))

        // 경산 경주 고령 구미 군위 김천 문경 봉화 상주 성주 안동
        // 영덕 영양 영주 영천 예천 울릉 울진 의성 청도 청송 칠곡 포항
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gb_gyeongsan))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gb_gyeongju))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gb_goryeong))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gb_gumi))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gb_gunwi))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gb_gimcheon))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gb_mungyeong))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gb_bonghwa))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gb_sangju))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gb_seongju))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gb_andong))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gb_yeongdeok))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gb_yeongyang))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gb_yeongju))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gb_yeongcheon))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gb_yecheon))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gb_ulreung))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gb_uljin))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gb_yuiseong))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gb_cheongdo))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gb_cheongsong))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gb_cilgok))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gb_pohang))

        // 거제 거창 고성 김해 남해 밀양 사천 산청 양산 의령 진주 창녕 창원 통영 하동 함안 함양 합천
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gn_geoje))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gn_geochang))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gn_goseong))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gn_gimhae))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gn_namhae))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gn_milyang))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gn_sacheon))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gn_sancheong))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gn_yangsan))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gn_yuiryeong))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gn_jinju))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gn_changnyeong))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gn_changwon))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gn_tongyeong))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gn_hadong))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gn_haman))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gn_hamyang))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_gn_hapcheon))

        // 고창 군산 김제 남원 무주 부안 순창 완주 익산 임실 장수 전주 정읍 진안
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_31_gochang))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_31_gunsan))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_31_gimje))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_31_namwon))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_31_muju))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_31_buan))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_31_sunchang))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_31_wanju))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_31_iksan))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_31_imsil))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_31_jangsu))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_31_jeonju))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_31_jeongeup))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_31_jinan))

        // 강진 고흥 곡성 광양 구례 나주 담양 목포 무안 보성 순천
        // 신안 여수 영광 영암 완도 장성 장흥 진도 함평 해남 화순
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_32_gangjin))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_32_goheung))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_32_gokseong))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_32_gwangyang))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_32_gurye))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_32_naju))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_32_damyang))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_32_mokpo))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_32_muan))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_32_boseong))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_32_suncheon))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_32_sinan))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_32_yeosu))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_32_yeonggwang))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_32_yeongam))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_32_wando))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_32_jangseong))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_32_jangheung))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_32_jindo))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_32_hampyeong))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_32_haenam))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_32_hwasun))

        // 서귀포 제주
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_9_jeju1))
        returnArray.add(BitmapFactory.decodeResource(context!!.resources, R.drawable.map_9_jeju2))

        return returnArray
    }

    fun makeBitmap(original : Bitmap, target : Bitmap) : Bitmap {
        var resultBitmap = Bitmap.createBitmap(original.width, original.height, original.config)

        var color_paint = Paint()
        //color_paint.setColorFilter(PorterDuffColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY))

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
