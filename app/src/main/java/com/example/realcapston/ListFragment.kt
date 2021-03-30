package com.example.realcapston

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
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

    private fun setAdapter(comList: ArrayList<ComModel>) {
        mRecyclerView.adapter = ComAdapter(comList) { com -> comItemClicked(com) }
        mRecyclerView.layoutManager = LinearLayoutManager(this.context)
        mRecyclerView.setHasFixedSize(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        this.button_test.setOnClickListener {
            activity?.let {
                val intent = Intent(context, DetailActivity::class.java)
                startActivity(intent)
            }
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitService = retrofit.create(RetrofitInterface::class.java)


        retrofitService.getRequest().enqueue(object : Callback<List<ComModel>> {
            override fun onResponse(
                call: Call<List<ComModel>>,
                response: Response<List<ComModel>>
            ) {
                val body = response.body()

                setAdapter(body as ArrayList<ComModel>)
            }

            override fun onFailure(call: Call<List<ComModel>>, t: Throwable) {
                Log.d("통신에 실패하였습니다.", t.message)
            }
        })
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.list)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.listmenu, menu)
    }

    private fun comItemClicked(com: ComModel?) {
//        Toast.makeText(context, "Clicked: ${com?.number}", Toast.LENGTH_SHORT).show()
        val nIntent = Intent(activity, DetailActivity::class.java)
        nIntent.putExtra("comId",com?.id)
        startActivity(nIntent)
    }
}