package com.seunggom.tripmanager


import android.graphics.*
import android.os.AsyncTask
import android.os.AsyncTask.THREAD_POOL_EXECUTOR
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.seunggom.tripmanager.model.RegionDTO
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_map.*
import java.lang.ref.WeakReference



class MapFragment() : Fragment() {

    var firestore: FirebaseFirestore? = null
    private var auth: FirebaseAuth? = null
    var regionDTO : RegionDTO? = null
    var task = Array(10) {BitmapWorkerTask()}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        firestore!!.collection("regions").document(auth?.currentUser!!.email!!).get().addOnCompleteListener { document ->
            if(document.isSuccessful) {
                regionDTO = document.result.toObject(RegionDTO::class.java)
                if (image_loaded == false) {
                    loadBitmap(getResourceID_1().toTypedArray(), map_1, 0, regionDTO!!.si_do_1)
                    loadBitmap(getResourceID_2().toTypedArray(), map_2, 1, regionDTO!!.si_do_2)
                    loadBitmap(getResourceID_3().toTypedArray(), map_3, 2, regionDTO!!.si_do_3)
                    loadBitmap(getResourceID_4().toTypedArray(), map_4, 3, regionDTO!!.si_do_4)
                    loadBitmap(getResourceID_5().toTypedArray(), map_5, 4, regionDTO!!.si_do_5)
                    loadBitmap(getResourceID_6().toTypedArray(), map_6, 5, regionDTO!!.si_do_6)
                    loadBitmap(getResourceID_7().toTypedArray(), map_7, 6, regionDTO!!.si_do_7)
                    loadBitmap(getResourceID_8().toTypedArray(), map_8, 7, regionDTO!!.si_do_8)
                    loadBitmap(getResourceID_9().toTypedArray(), map_9, 8, regionDTO!!.si_do_9)
                    loadBitmap(getResourceID_10().toTypedArray(), map_10, 9, regionDTO!!.si_do_10)
                }
            }
            else {
                var new_regionDTO = RegionDTO()
                firestore?.collection("regions")?.document(auth?.currentUser!!.email!!)?.set(new_regionDTO)
                regionDTO = new_regionDTO
            }
        }
        Glide.with(this).load(R.drawable.map__outline).into(map_outline)
        Glide.with(this).load(R.drawable.map_name).into(map_name)
            if(image_loaded == true) {
                Glide.with(this).load(mapimages[0]).into(map_1)
                Glide.with(this).load(mapimages[1]).into(map_2)
                Glide.with(this).load(mapimages[2]).into(map_3)
                Glide.with(this).load(mapimages[3]).into(map_4)
                Glide.with(this).load(mapimages[4]).into(map_5)
                Glide.with(this).load(mapimages[5]).into(map_6)
                Glide.with(this).load(mapimages[6]).into(map_7)
                Glide.with(this).load(mapimages[7]).into(map_8)
                Glide.with(this).load(mapimages[8]).into(map_9)
                Glide.with(this).load(mapimages[9]).into(map_10)
                loading_progressBar.visibility = View.GONE
                loading_text.visibility = View.GONE


            }
        //progressBar2.visibility = View.GONE
        var mainActivity = activity as MainActivity
        mainActivity.progressBar.visibility = View.INVISIBLE

    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        for(i in 0..9) task[i].cancel(true)

        // 참고 사이트 : https://woovictory.github.io/2019/08/03/AsyncTask/
    }


    //https://forums.developer.amazon.com/questions/12734/illegalargumentexception-on-lockcanvas.html


    fun loadBitmap(resId : Array<Int>, imageView: ImageView, index: Int, region: ArrayList<Int>) {
//        task[index] = BitmapWorkerTask()
        task[index].BitmapWorkerTask(imageView, region)

        task[index].executeOnExecutor(THREAD_POOL_EXECUTOR, *resId)
    }

//https://hoyytada.github.io/android/2017/12/10/dev-android-01.html

    fun getResourceID_1() : List<Int> {
        var IDarray = mutableListOf<Int>()

        IDarray.add(R.drawable.map_0_seoul)
        IDarray.add(R.drawable.map_0_incheon)
        IDarray.add(R.drawable.map_0_daejeon)
        IDarray.add(R.drawable.map_0_sejong)
        IDarray.add(R.drawable.map_0_gwangju)
        IDarray.add(R.drawable.map_0_ulsan)
        IDarray.add(R.drawable.map_0_busan)
        IDarray.add(R.drawable.map_0_daegu)

        return IDarray
    }

    fun getResourceID_2() : List<Int> {
        var IDarray = mutableListOf<Int>()

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

        return IDarray
    }

    fun getResourceID_3() : List<Int> {
        var IDarray = mutableListOf<Int>()

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

        return IDarray
    }

    fun getResourceID_4() : List<Int> {
        var IDarray = mutableListOf<Int>()

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

        return IDarray
    }

    fun getResourceID_5() : List<Int> {
        var IDarray = mutableListOf<Int>()


// 계룡 공주 금산 논산 당진 보령 부여 서산 서천 아산 예산 천안 청양 태안 홍성
        IDarray.add(R.drawable.map_22_gyeryong)
        IDarray.add(R.drawable.map_22_gongju)
        IDarray.add(R.drawable.map_22_geumsan)
        IDarray.add(R.drawable.map_22_nonsan)
        IDarray.add(R.drawable.map_22_dangjin)
        IDarray.add(R.drawable.map_22_boryeong)
        IDarray.add(R.drawable.map_22_buyeo)
        IDarray.add(R.drawable.map_22_seosan)
        IDarray.add(R.drawable.map_22_seocheon)
        IDarray.add(R.drawable.map_22_asan)
        IDarray.add(R.drawable.map_22_yesan)
        IDarray.add(R.drawable.map_22_cheonan)
        IDarray.add(R.drawable.map_22_cheongyang)
        IDarray.add(R.drawable.map_22_taean)
        IDarray.add(R.drawable.map_22_hongseong)

        return IDarray
    }

    fun getResourceID_6() : List<Int> {
        var IDarray = mutableListOf<Int>()

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

        return IDarray
    }

    fun getResourceID_7() : List<Int> {
        var IDarray = mutableListOf<Int>()

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

        return IDarray
    }

    fun getResourceID_8() : List<Int> {
        var IDarray = mutableListOf<Int>()

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

        return IDarray
    }

    fun getResourceID_9() : List<Int> {
        var IDarray = mutableListOf<Int>()

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

        return IDarray
    }

    fun getResourceID_10() : List<Int> {
        var IDarray = mutableListOf<Int>()

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

    interface loadCompleteListener {
        fun changeSetting()
    }

    val listener:loadCompleteListener = MainActivity()

    inner class BitmapWorkerTask : AsyncTask<Int, Void, Bitmap>() {

        lateinit var imageViewReference: WeakReference<ImageView>
        lateinit var region : ArrayList<Int>
        fun BitmapWorkerTask(imageView: ImageView, region_info : ArrayList<Int>) {
            // WeakReference 를 사용하는 이유는 image 처럼 메모리를 많이 차지하는 객체에 대한 가비지컬렉터를 보장하기 위해서입니다.

            imageViewReference = WeakReference(imageView)
            region = region_info

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
            var color_paint1 = Paint()
            var color_paint2 = Paint()

            bitmap = makeBitmap(image1, image2, chooseColor(region[0]), chooseColor(region[1]))
            if(!image1.isRecycled) image1.recycle()
            if(!image2.isRecycled) image2.recycle()

            var null_paint = Paint()

            for(i in 2..image_num-1) {
                var newimage : Bitmap? = null
                newimage = BitmapFactory.decodeResource(context!!.resources, params[i]!!)
                bitmap = makeBitmap(bitmap!!, newimage, null_paint, chooseColor(region[i]))

                if(!newimage.isRecycled) newimage.recycle()
            }


            return bitmap!!
        }


        fun makeBitmap(original : Bitmap, target : Bitmap, original_paint : Paint, target_paint : Paint) : Bitmap {
            var surfaceView: SurfaceView? = null


            var resultBitmap = Bitmap.createBitmap(original.width, original.height, original.config)

            //var holder : SurfaceHolder = surfaceView!!.holder!!
            var canvas = Canvas(resultBitmap)
            //canvas = holder.lockCanvas()
            canvas.setBitmap(resultBitmap)


            canvas.drawBitmap(original, Matrix(), original_paint)
            canvas.drawBitmap(target, Matrix(), target_paint)

            //holder.unlockCanvasAndPost(canvas)


            return resultBitmap
        }


        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
            if (imageViewReference != null && result != null) {
                var imageView = imageViewReference.get()
                if (imageView != null) {
                    Glide.with(this@MapFragment).load(result).into(imageView)
                    mapimages.add(result)
                    if(mapimages.size == 10) {
                        loading_progressBar.visibility = View.GONE
                        loading_text.visibility = View.GONE
                        listener.changeSetting()
                    }
                }
            }

        }

        fun chooseColor(a : Int) : Paint {
            var p = Paint()
            if(a == 0) return p
            else if(a == 1) {
                p.colorFilter = PorterDuffColorFilter(resources.getColor(R.color.map_1), PorterDuff.Mode.MULTIPLY)
            }
            else if(a>1 && a<6) p.colorFilter = PorterDuffColorFilter(resources.getColor(R.color.map_2to5), PorterDuff.Mode.MULTIPLY)
            else if( a>=6 && a<10) p.colorFilter = PorterDuffColorFilter(resources.getColor(R.color.map_6to10), PorterDuff.Mode.MULTIPLY)
            else p.colorFilter = PorterDuffColorFilter(resources.getColor(R.color.map_11), PorterDuff.Mode.MULTIPLY)
            return p
        }

    }

}

/* 참고 사이트
* https://webnautes.tistory.com/1082
*
*
* */