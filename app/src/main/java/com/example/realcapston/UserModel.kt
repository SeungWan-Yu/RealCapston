package com.example.realcapston

import com.google.gson.annotations.SerializedName

data class UserModel(

    @SerializedName("id") val id: String,

    @SerializedName("number") val name: String,

    @SerializedName("os") val os: String,

    @SerializedName("hostname") val hostname: String,

    @SerializedName("cpu-use") val cpuuse: String,

    @SerializedName("cpu-sys") val cpusys: String,

    @SerializedName("disk-use") val diskuse: String,

    @SerializedName("disk-total") val disktotal: String,

    @SerializedName("mem-use") val memuse: String,

    @SerializedName("mem-total") val memtotal: String,

    @SerializedName("cpu-top") val cputop: String,

    @SerializedName("mem-top") val memtop: String,

    @SerializedName("network-rx-byte") val networkrxbyte: String,

    @SerializedName("network-tx-byte") val networktxbyte: String,

    @SerializedName("network-rx-packet") val networkrxpacket: String,

    @SerializedName("network-tx-packet") val networktxpacket: String,

    @SerializedName("timestamp") val timestamp: String
)
