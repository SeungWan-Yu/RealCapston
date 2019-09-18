package com.example.realcapston

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        h_tv_id.setText(App.prefs.myId+"님 로그인!")

        h_bt_logout.setOnClickListener {
            App.prefs.myId = ""
            val LogoutIntent = Intent(activity,LoginActivity::class.java)
            startActivity(LogoutIntent)
        }
    }
}