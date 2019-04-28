package com.lk.memobar2.main

import android.os.Build

/**
 * Erstellt von Lena am 28/04/2019.
 */
object Utils {

    const val DIALOG_TITLE_RESOURCE = "dialogTitleResource"
    const val MEMO_KEY = "memo"
    const val PREF_KEY_NOTIFICATION = "pref_notification"

    fun isBuildVersionGreaterThan(version: Int): Boolean
        = Build.VERSION.SDK_INT >= version

}