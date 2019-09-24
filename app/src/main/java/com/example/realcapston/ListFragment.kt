package com.example.realcapston

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private val url = "http://52.78.192.102/api/log/all"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://52.78.192.102/api/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val retrofitService = retrofit.create(RetrofitInterface::class.java)

        button123.setOnClickListener {

            val stringRequest = StringRequest(
                Request.Method.GET,
                url,
                Response.Listener { responseString->
                    tv_result.setText(responseString)
                },
                Response.ErrorListener {vollyError->
                    Toast.makeText(activity, "통신실패..", Toast.LENGTH_SHORT).show()
                }
            )
            Volley.newRequestQueue(activity).add(stringRequest)

//            retrofitService.getRequest().enqueue(object: Callback<UserModel> {
//                override fun onFailure(call: Call<UserModel>, t: Throwable) {
//                    Toast.makeText(activity, "실패..", Toast.LENGTH_SHORT).show()
//                }
//
//                override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
//                    val body = response.body().toString()
//                    Toast.makeText(activity, response.body().toString(), Toast.LENGTH_SHORT).show()
//                }
//
//            })
        }
    }
}