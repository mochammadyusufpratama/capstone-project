package com.dicoding.capstoneproject

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val pref: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = pref.edit()

    companion object {
        private const val PREF_NAME = "YourAppPref"
        private const val IS_LOGGED_IN = "IsLoggedIn"
    }

    init {
        editor.apply()
    }

    fun setLogin(isLoggedIn: Boolean) {
        editor.putBoolean(IS_LOGGED_IN, isLoggedIn)
        editor.apply()
    }

    fun isLoggedIn(): Boolean {
        return pref.getBoolean(IS_LOGGED_IN, false)
    }

}

