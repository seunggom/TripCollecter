package com.seunggom.tripmanager

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.seunggom.tripmanager.model.RegionDTO
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.util.*


class LoginActivity : AppCompatActivity() {

    var auth: FirebaseAuth? = null // firebase authentication 관리 클래스
    var callbackManager: CallbackManager? = null // facebook 로그인 처리 결과 관리 클래스
    var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance() //firebase login 통합 관리하는 object


        email_login_button.setOnClickListener { emailLogin() }
        facebook_login_button.setOnClickListener { facebookLogin() }

        callbackManager = CallbackManager.Factory.create()
    }

    fun moveMainPage(user : FirebaseUser?) {
        if (user != null) {
            toast(getString(R.string.signin_complete))
            startActivity<MainActivity>()
            finish()
        }
    }

    // facebook 토큰을 firebase로 넘겨주는 코드
    fun handleFacebookAccessToken(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        auth?.signInWithCredential(credential)?.addOnCompleteListener {
                task -> progress_bar.visibility = View.GONE
            // 다음 페이지 이동
            if (task.isSuccessful) (moveMainPage(auth?.currentUser))
        }
    }

    fun facebookLogin() {
        progress_bar.visibility = View.VISIBLE
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email")) // 이메일 정보만 가져옴
        LoginManager.getInstance().registerCallback(callbackManager, object: FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
                handleFacebookAccessToken(result?.accessToken)
            }
            override fun onCancel() {
                progress_bar.visibility = View.GONE
            }
            override fun onError(error: FacebookException?) {
                progress_bar.visibility = View.GONE
            }
        })
    }

    // 이메일 회원 가입 및 로그인 메소드
    fun createAndLoginEmail() {
        auth?.createUserWithEmailAndPassword(email_edittext.text.toString(), password_edittext.text.toString())?.addOnCompleteListener {
            task -> progress_bar.visibility = View.GONE
            if (task.isSuccessful) {
                // 아이디 생성이 성공했을 경우
                firestore = FirebaseFirestore.getInstance()
                var regionDTO = RegionDTO()
                firestore?.collection("regions")?.document(email_edittext.text.toString())?.set(regionDTO)
                toast(getString(R.string.signup_complete))
                moveMainPage(auth?.currentUser)
            }
            else if(task.exception?.message.isNullOrEmpty()) {
                // 회원가입 에러가 발생한 경우
                toast(task.exception!!.message!!)
            }
            else {
                signinEmail()
            }
        }
    }

    fun emailLogin() {
        if(email_edittext.text.toString().isNullOrEmpty() || password_edittext.text.toString().isNullOrEmpty()) {
            toast(getString(R.string.signout_fail_null))
        }
        else {
            progress_bar.visibility = View.VISIBLE
            createAndLoginEmail()
        }
    }
    
    fun signinEmail() {
        auth?.signInWithEmailAndPassword(email_edittext.text.toString(), password_edittext.text.toString())?.addOnCompleteListener {
            task ->  progress_bar.visibility = View.GONE
            if (task.isSuccessful) {
                // 로그인 성공 및 다음 페이지 호출
                toast("로그인 성공")
                moveMainPage(auth?.currentUser)
            }
            else {
                toast(task.exception!!.message!!)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // facebook sdk로 값 넘겨주기
        callbackManager?.onActivityResult(requestCode, resultCode, data)

    }

    override fun onStart() {
        super.onStart()

        // 자동 로그인 설정
        moveMainPage(auth?.currentUser)
    }
}
