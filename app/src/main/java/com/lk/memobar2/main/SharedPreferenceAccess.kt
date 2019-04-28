package com.lk.memobar2.main

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager

/**
 * Erstellt von Lena am 28/04/2019.
 */
object SharedPreferenceAccess {

    fun readString(key: String, context: Context): String {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        return sp.getString(key, "") ?: ""
    }

    fun putString(key: String, value: String, context: Context) {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        sp.edit(true) {
            putString(key, value)
        }
    }

}