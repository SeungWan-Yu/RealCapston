package com.example.realcapston

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val retrofit = Retrofit.Builder()
            .baseUrl("http://52.78.192.102/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitService = retrofit.create(RetrofitInterface::class.java)

        retrofitService.getRequest().enqueue(object:Callback<UserModel>{
            override fun onFailure(call: Call<UserModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
            }

        })
    }
}