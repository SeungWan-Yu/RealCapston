package com.example.realcapston

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity() {

    private val id = "admin"
    private val password = "1234"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (App.prefs.myId != ""){
            startActivity<MainActivity>()
        }else{
            bt_login.setOnClickListener {
                val inputLogin = et_id.text.trim().toString()
                val inputPassword = et_pw.text.trim().toString()


                if (inputLogin == id && inputPassword == password) {
                    App.prefs.myId = inputLogin
//            'set 실행'
                    startActivity<MainActivity>()
                    finish()
                }else{
                    if (inputLogin.isNullOrEmpty() && inputPassword.isNullOrEmpty()) {
                        toast("ID와 비밀번호를 입력해주세요.")
                    }
                    else if(inputLogin!=id) {
                        toast("존재하지 않는 ID입니다.")
                    }
                    else {
                        toast("비밀번호가 틀렸습니다.")
                    }
                }
            }

            bt_singup.setOnClickListener {
                startActivity<SIgnUpActivity>()
                finish()
            }
        }
    }
}
