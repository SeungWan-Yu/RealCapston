package com.example.realcapston

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences (context: Context){

    val PREFS_FILENAME = "prefs"
    val PREF_KEY_MY_ID = "myEditText"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var myId: String
        get() = prefs.getString(PREF_KEY_MY_ID, "")
        set(value) = prefs.edit().putString(PREF_KEY_MY_ID, value).apply()

}