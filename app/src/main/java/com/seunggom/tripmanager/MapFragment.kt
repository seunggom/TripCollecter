package com.seunggom.tripmanager


import android.graphics.*
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.seunggom.tripmanager.model.RegionDTO
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_map.*
import java.lang.ref.WeakReference



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

        //image_glide()
        Glide.with(this).load(R.drawable.map__outline).into(map_outline)
        loadBitmap(getResourceID().toTypedArray(), map_1)

        //progressBar2.visibility = View.GONE
        var mainActivity = activity as MainActivity
        mainActivity.progressBar.visibility = View.INVISIBLE

    }


    //https://forums.developer.amazon.com/questions/12734/illegalargumentexception-on-lockcanvas.html


    fun loadBitmap(resId : Array<Int>, imageView: ImageView) {
        var task : BitmapWorkerTask =  BitmapWorkerTask()
        task.BitmapWorkerTask(imageView)

        task.execute(*resId)

    }


    fun image_glide() {
        //mainActivity.progressBar.visibility = View.VISIBLE

        /*var images = getResourceBitmap()
        var image_num = images.size

        var bitmap = makeBitmap(images[0], images[1])
        for(i in 2..image_num-1) {
            bitmap = makeBitmap(bitmap, images[i])
        }
        Glide.with(this).load(bitmap).into(map_1)*/
        Glide.with(this).load(R.drawable.map__outline).into(map_outline)

        //mainActivity.progressBar.visibility = View.GONE

    }
//https://hoyytada.github.io/android/2017/12/10/dev-android-01.html

    fun getResourceID() : List<Int> {
        var IDarray = mutableListOf<Int>()

        IDarray.add(R.drawable.map_0_seoul)
        IDarray.add(R.drawable.map_0_incheon)
        IDarray.add(R.drawable.map_0_daejeon)
        IDarray.add(R.drawable.map_0_sejong)
        IDarray.add(R.drawable.map_0_gwangju)
        IDarray.add(R.drawable.map_0_ulsan)
        IDarray.add(R.drawable.map_0_busan)
        IDarray.add(R.drawable.map_0_daegu)
//가평01 고양02 과천03 광명04 광주05 구리06 군포07 김포08 남양주09 동두천10 부천11 성남12 수원13 시흥14 안산15 안성16
//안양17 양주18 양평19 여주20 연천21 오산22 용인23 의왕24 의정부25 이천26 파주27 평택28 포천29 하남30 화성31
        IDarray.add(R.drawable.map_1_gp)
        IDarray.add(R.drawable.map_1_goyang)
        IDarray.add(R.drawable.map_1_gc)
        IDarray.add(R.drawable.map_1_gm)
        IDarray.add(R.drawable.map_1_gj)
        IDarray.add(R.drawable.map_1_guri)
        IDarray.add(R.drawable.map_1_gunpo)
        IDarray.add(R.drawable.map_1_gimpo)
        IDarray.add(R.drawable.map_1_nyju)
        IDarray.add(R.drawable.map_1_dongdc)
        IDarray.add(R.drawable.map_1_bc)
        IDarray.add(R.drawable.map_1_snm)
        IDarray.add(R.drawable.map_1_suwon)
        IDarray.add(R.drawable.map_1_sh)
        IDarray.add(R.drawable.map_1_ansan)
        IDarray.add(R.drawable.map_1_anseong)
        IDarray.add(R.drawable.map_1_anyang)
        IDarray.add(R.drawable.map_1_yangju)
        IDarray.add(R.drawable.map_1_yp)
        IDarray.add(R.drawable.map_1_yeoju)
        IDarray.add(R.drawable.map_1_yeoncheon)
        IDarray.add(R.drawable.map_1_os)
        IDarray.add(R.drawable.map_1_yi)
        IDarray.add(R.drawable.map_1_euiwang)
        IDarray.add(R.drawable.map_1_ejb)
        IDarray.add(R.drawable.map_1_ic)
        IDarray.add(R.drawable.map_1_paju)
        IDarray.add(R.drawable.map_1_pt)
        IDarray.add(R.drawable.map_1_pocheon)
        IDarray.add(R.drawable.map_1_hanam)
        IDarray.add(R.drawable.map_1_hs)

// 강원도
// 강릉 고성 동해 삼척 속초 양구 양양 영월 원주 인제 정선 철원 춘천 태백 평창 홍천 화천 횡성
        IDarray.add(R.drawable.map_2_gr)
        IDarray.add(R.drawable.map_2_gs)
        IDarray.add(R.drawable.map_2_dh)
        IDarray.add(R.drawable.map_2_sc)
        IDarray.add(R.drawable.map_2_sokcho)
        IDarray.add(R.drawable.map_2_yg)
        IDarray.add(R.drawable.map_2_yy)
        IDarray.add(R.drawable.map_2_yw)
        IDarray.add(R.drawable.map_2_wj)
        IDarray.add(R.drawable.map_2_ij)
        IDarray.add(R.drawable.map_2_js)
        IDarray.add(R.drawable.map_2_cw)
        IDarray.add(R.drawable.map_2_cc)
        IDarray.add(R.drawable.map_2_tb)
        IDarray.add(R.drawable.map_2_pc)
        IDarray.add(R.drawable.map_2_hc)
        IDarray.add(R.drawable.map_2_hwacheon)
        IDarray.add(R.drawable.map_2_hs)

// 괴산 단양 보은 영동 옥천 음성 제천 증평 진천 청주 충주
        IDarray.add(R.drawable.map_21_goesan)
        IDarray.add(R.drawable.map_21_danyang)
        IDarray.add(R.drawable.map_21_boeun)
        IDarray.add(R.drawable.map_21_yeongdong)
        IDarray.add(R.drawable.map_21_okcheon)
        IDarray.add(R.drawable.map_21_eumseong)
        IDarray.add(R.drawable.map_21_jecheon)
        IDarray.add(R.drawable.map_21_jeungpyeong)
        IDarray.add(R.drawable.map_21_jincheon)
        IDarray.add(R.drawable.map_21_cheongju)
        IDarray.add(R.drawable.map_21_chungju)

// 계룡 공주 금산 논산 당진 보령 부여 서산 서천 아산 예산 천안 청양 태안 홍성
        IDarray.add(R.drawable.map_22_gyeryong)
        IDarray.add(R.drawable.map_22_gongju)
        IDarray.add(R.drawable.map_22_geumsan)
        IDarray.add(R.drawable.map_22_nonsan)
        IDarray.add(R.drawable.map_22_dangjin)
        IDarray.add(R.drawable.map_22_boryeong)
        IDarray.add(R.drawable.map_22_buyeo)
        IDarray.add(R.drawable.map_22_seosan)
        IDarray.add(R.drawable.map_22_asan)
        IDarray.add(R.drawable.map_22_yesan)
        IDarray.add(R.drawable.map_22_cheonan)
        IDarray.add(R.drawable.map_22_cheongyang)
        IDarray.add(R.drawable.map_22_taean)
        IDarray.add(R.drawable.map_22_hongseong)

// 경산 경주 고령 구미 군위 김천 문경 봉화 상주 성주 안동
// 영덕 영양 영주 영천 예천 울릉 울진 의성 청도 청송 칠곡 포항
        IDarray.add(R.drawable.map_gb_gyeongsan)
        IDarray.add(R.drawable.map_gb_gyeongju)
        IDarray.add(R.drawable.map_gb_goryeong)
        IDarray.add(R.drawable.map_gb_gumi)
        IDarray.add(R.drawable.map_gb_gunwi)
        IDarray.add(R.drawable.map_gb_gimcheon)
        IDarray.add(R.drawable.map_gb_mungyeong)
        IDarray.add(R.drawable.map_gb_bonghwa)
        IDarray.add(R.drawable.map_gb_sangju)
        IDarray.add(R.drawable.map_gb_seongju)
        IDarray.add(R.drawable.map_gb_andong)
        IDarray.add(R.drawable.map_gb_yeongdeok)
        IDarray.add(R.drawable.map_gb_yeongyang)
        IDarray.add(R.drawable.map_gb_yeongju)
        IDarray.add(R.drawable.map_gb_yeongcheon)
        IDarray.add(R.drawable.map_gb_yecheon)
        IDarray.add(R.drawable.map_gb_ulreung)
        IDarray.add(R.drawable.map_gb_uljin)
        IDarray.add(R.drawable.map_gb_yuiseong)
        IDarray.add(R.drawable.map_gb_cheongdo)
        IDarray.add(R.drawable.map_gb_cheongsong)
        IDarray.add(R.drawable.map_gb_cilgok)
        IDarray.add(R.drawable.map_gb_pohang)

// 거제 거창 고성 김해 남해 밀양 사천 산청 양산 의령 진주 창녕 창원 통영 하동 함안 함양 합천
        IDarray.add(R.drawable.map_gn_geoje)
        IDarray.add(R.drawable.map_gn_geochang)
        IDarray.add(R.drawable.map_gn_goseong)
        IDarray.add(R.drawable.map_gn_gimhae)
        IDarray.add(R.drawable.map_gn_namhae)
        IDarray.add(R.drawable.map_gn_milyang)
        IDarray.add(R.drawable.map_gn_sacheon)
        IDarray.add(R.drawable.map_gn_sancheong)
        IDarray.add(R.drawable.map_gn_yangsan)
        IDarray.add(R.drawable.map_gn_yuiryeong)
        IDarray.add(R.drawable.map_gn_jinju)
        IDarray.add(R.drawable.map_gn_changnyeong)
        IDarray.add(R.drawable.map_gn_changwon)
        IDarray.add(R.drawable.map_gn_tongyeong)
        IDarray.add(R.drawable.map_gn_hadong)
        IDarray.add(R.drawable.map_gn_haman)
        IDarray.add(R.drawable.map_gn_hamyang)
        IDarray.add(R.drawable.map_gn_hapcheon)

// 고창 군산 김제 남원 무주 부안 순창 완주 익산 임실 장수 전주 정읍 진안
        IDarray.add(R.drawable.map_31_gochang)
        IDarray.add(R.drawable.map_31_gunsan)
        IDarray.add(R.drawable.map_31_gimje)
        IDarray.add(R.drawable.map_31_namwon)
        IDarray.add(R.drawable.map_31_muju)
        IDarray.add(R.drawable.map_31_buan)
        IDarray.add(R.drawable.map_31_sunchang)
        IDarray.add(R.drawable.map_31_wanju)
        IDarray.add(R.drawable.map_31_iksan)
        IDarray.add(R.drawable.map_31_imsil)
        IDarray.add(R.drawable.map_31_jangsu)
        IDarray.add(R.drawable.map_31_jeonju)
        IDarray.add(R.drawable.map_31_jeongeup)
        IDarray.add(R.drawable.map_31_jinan)

// 강진 고흥 곡성 광양 구례 나주 담양 목포 무안 보성 순천
// 신안 여수 영광 영암 완도 장성 장흥 진도 함평 해남 화순
        IDarray.add(R.drawable.map_32_gangjin)
        IDarray.add(R.drawable.map_32_goheung)
        IDarray.add(R.drawable.map_32_gokseong)
        IDarray.add(R.drawable.map_32_gwangyang)
        IDarray.add(R.drawable.map_32_gurye)
        IDarray.add(R.drawable.map_32_naju)
        IDarray.add(R.drawable.map_32_damyang)
        IDarray.add(R.drawable.map_32_mokpo)
        IDarray.add(R.drawable.map_32_muan)
        IDarray.add(R.drawable.map_32_boseong)
        IDarray.add(R.drawable.map_32_suncheon)
        IDarray.add(R.drawable.map_32_sinan)
        IDarray.add(R.drawable.map_32_yeosu)
        IDarray.add(R.drawable.map_32_yeonggwang)
        IDarray.add(R.drawable.map_32_yeongam)
        IDarray.add(R.drawable.map_32_wando)
        IDarray.add(R.drawable.map_32_jangseong)
        IDarray.add(R.drawable.map_32_jangheung)
        IDarray.add(R.drawable.map_32_jindo)
        IDarray.add(R.drawable.map_32_hampyeong)
        IDarray.add(R.drawable.map_32_haenam)
        IDarray.add(R.drawable.map_32_hwasun)

// 서귀포 제주
        IDarray.add(R.drawable.map_9_jeju1)
        IDarray.add(R.drawable.map_9_jeju2)

        return IDarray
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


    inner class BitmapWorkerTask : AsyncTask<Int, Void, Bitmap>() {

        lateinit var imageViewReference: WeakReference<ImageView>

        fun BitmapWorkerTask(imageView: ImageView) {
            // WeakReference 를 사용하는 이유는 image 처럼 메모리를 많이 차지하는 객체에 대한 가비지컬렉터를 보장하기 위해서입니다.

            imageViewReference = WeakReference(imageView)
            //출처: https://ismydream.tistory.com/130 [창조적고찰]
        }

        override fun doInBackground(vararg params: Int?): Bitmap {
         /*
            var images = getResourceBitmap()
            var image_num = images.size

            var bitmap = makeBitmap(images[0], images[1])
            for(i in 2..image_num-1) {
                bitmap = makeBitmap(bitmap, images[i])
            }

            return bitmap
            */

            var image_num = params.size

            var bitmap : Bitmap? = null
            var image1 = BitmapFactory.decodeResource(context!!.resources, params[0]!!)
            var image2 = BitmapFactory.decodeResource(context!!.resources, params[1]!!)
            bitmap = makeBitmap(image1, image2)
            if(!image1.isRecycled) image1.recycle()
            if(!image2.isRecycled) image2.recycle()

            for(i in 2..image_num-1) {
                var newimage : Bitmap? = null
                newimage = BitmapFactory.decodeResource(context!!.resources, params[i]!!)
                bitmap = makeBitmap(bitmap!!, newimage)

                if(!newimage.isRecycled) newimage.recycle()
            }

            return bitmap!!
        }



/*
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
        }*/

        fun makeBitmap(original : Bitmap, target : Bitmap) : Bitmap {
            var surfaceView: SurfaceView? = null


            var resultBitmap = Bitmap.createBitmap(original.width, original.height, original.config)

            var color_paint = Paint()
            //color_paint.setColorFilter(PorterDuffColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY))

            var holder : SurfaceHolder = surfaceView!!.holder!!
            var canvas = Canvas(resultBitmap)
            canvas = holder.lockCanvas()
            canvas.setBitmap(resultBitmap)


            canvas.drawBitmap(original, Matrix(), null)
            canvas.drawBitmap(target, Matrix(), color_paint)

            holder.unlockCanvasAndPost(canvas)


            return resultBitmap
        }


        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
            if (imageViewReference != null && result != null) {
                var imageView = imageViewReference.get()
                if (imageView != null) {
                    Glide.with(this@MapFragment).load(result).into(imageView)
                }
            }

        }

    }
}
