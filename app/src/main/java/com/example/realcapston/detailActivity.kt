package com.example.realcapston

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class detailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        if (intent.hasExtra("comId")) {
            val comId= intent.getStringExtra("comId")
            Toast.makeText(this, "전달된 값은 $comId", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this, "전달된 이름이 없습니다", Toast.LENGTH_SHORT).show()
        }
    }
}
