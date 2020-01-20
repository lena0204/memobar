package com.lk.memobar2.notification

import android.app.*
import android.content.Context
import com.lk.memobar2.database.MemoEntity

/**
 * Erstellt von Lena am 28/04/2019.
 */
class MemoNotificationManager(private val context: Context) {

    private val TAG = "NotificationManager"
    private val systemNotManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun handleMemosUpdate(memos: List<MemoEntity>) {
        val filteredMemos = memos.filter { memo -> memo.isActive }
        if(filteredMemos.isEmpty()){
            systemNotManager.cancel(MemosNotification.NOTIFICATION_ID)
        } else {
            val notification =
                MemosNotification.buildNotification(context, filteredMemos)
            launchNotification(notification)
        }
    }

    fun handleMemosUpdate(memos: String){
        if(memos.trim() != "") {
            val notification =
                MemosNotification.buildNotification(context, memos)
            launchNotification(notification)
        }
    }

    private fun launchNotification(not: Notification) {
        not.flags = Notification.FLAG_NO_CLEAR
        systemNotManager.notify(MemosNotification.NOTIFICATION_ID, not)
    }

}