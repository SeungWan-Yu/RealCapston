package com.example.realcapston

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface RetrofitInterface {

    @GET("log/all")
    fun getRequest():Call<UserModel>

    @POST("get/user/")
    fun Login(
        @Body request : LoginRequest
    ): Call<LoginModel>

}