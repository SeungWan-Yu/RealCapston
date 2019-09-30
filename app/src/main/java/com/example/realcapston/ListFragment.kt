package com.example.realcapston

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListFragment : Fragment() {

    private val url = "https://api.overlog.io/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    private fun setAdapter(comList : ArrayList<ComModel>){
        val mAdapter = ComAdapter(comList, this!!.activity!!)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.setHasFixedSize(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitService = retrofit.create(RetrofitInterface::class.java)


        retrofitService.getRequest().enqueue(object : Callback<List<ComModel>>{
            override fun onResponse(call: Call<List<ComModel>>, response: Response<List<ComModel>>) {
                val body = response.body()
                setAdapter(body as ArrayList<ComModel>)
//                asd
            }

            override fun onFailure(call: Call<List<ComModel>>, t: Throwable) {
                Log.d("this is error",t.message)
            }
        })

    }
}