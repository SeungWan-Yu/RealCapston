package com.example.realcapston

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitService = retrofit.create(RetrofitInterface::class.java)

        button123.setOnClickListener {

//            val stringRequest = StringRequest(
//                Request.Method.GET,
//                url,
//                Response.Listener { responseString->
//                    tv_result.setText(responseString)
//                },
//                Response.ErrorListener {vollyError->
//                    Toast.makeText(activity, "통신실패..", Toast.LENGTH_SHORT).show()
//
//                }
//            )
//            Volley.newRequestQueue(activity).add(stringRequest)

            retrofitService.getRequest().enqueue(object: Callback<List<UserModel>>{
                override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {

                    Toast.makeText(activity, "실패.."+t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<List<UserModel>>, response: Response<List<UserModel>>) {
                    val body = response.body()
                    Toast.makeText(activity, body?.toString(), Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}