package com.example.realcapston

import retrofit2.Call
import retrofit2.http.GET


interface RetrofitInterface {

    @GET("log/all")
    fun getRequest():Call<List<UserModel>>

//    @POST("get/user/")
//    fun Login(
//        @Body request : LoginRequest
//    ): Call<LoginModel>

}