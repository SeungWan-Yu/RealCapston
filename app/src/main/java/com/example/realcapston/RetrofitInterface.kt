package com.example.realcapston

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface RetrofitInterface {

    @GET("log/all")
    fun getRequest():Call<List<ComModel>>

    @POST("auth/user")
    fun Login(
        @Body request : LoginRequest
    ): Call<LoginModel>

}