package com.example.realcapston

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        h_tv_id.text = App.prefs.myId+"님 로그인!"

        setHasOptionsMenu(true)
//        (activity as AppCompatActivity).setSupportActionBar(toolbar_name)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.homemenu, menu)
    }
}
