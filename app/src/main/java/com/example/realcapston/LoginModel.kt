package com.example.realcapston

import com.google.gson.annotations.SerializedName

data class LoginModel(

    @SerializedName("user-exists") val userexists : Boolean,

    @SerializedName("id") val id : String,

    @SerializedName("name") val name: String,

    @SerializedName("register-date") val registerdate : String,

    @SerializedName("modify-date") val modifydate : String,

    @SerializedName("email") val email : String,

    @SerializedName("result") val result : String
)
