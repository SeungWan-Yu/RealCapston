package com.example.realcapston

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (App.prefs.myId != ""){
            startActivity<MainActivity>()
        } else {

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.overlog.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val retrofitService = retrofit.create(RetrofitInterface::class.java)

            bt_login.setOnClickListener {
                val inputLogin = et_id.text.trim().toString()
                val inputPassword = et_pw.text.trim().toString()

                App.prefs.myId = inputLogin

//                startActivity<MainActivity>()
//                finish()

                retrofitService.Login(LoginRequest(inputLogin, inputPassword))
                    .enqueue(object : Callback<LoginModel> {
                        override fun onResponse(
                            call: Call<LoginModel>, response: Response<LoginModel>
                        ) {
                            val body = response.body()
                            if (body?.userexists.toString()=="true") {
                                val body = response.body().toString()
                                body.let {
                                    App.prefs.myId = inputLogin
//            'set 실행'
                                    startActivity<MainActivity>()
                                    finish()
                                }
                            } else if (inputLogin.isEmpty() && inputPassword.isEmpty()) {
                                toast("ID와 비밀번호를 입력해주세요.")
                            } else {
                                toast("존재하지 않는 ID이거나, 비밀번호가 틀렸습니다.")
                            }

                        }

                        override fun onFailure(call: Call<LoginModel>, t: Throwable) {
//                            toast("통신에 실패하였습니다.")
                            Log.d("통신에 실패하였습니다.",t.message)
                        }
                    })

            }

            bt_singup.setOnClickListener {
                startActivity<SIgnUpActivity>()
                finish()
            }
        }
    }
}
