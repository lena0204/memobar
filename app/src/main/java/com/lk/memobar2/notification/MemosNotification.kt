package com.lk.memobar2.notification

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.lk.memobar2.R
import com.lk.memobar2.database.MemoEntity
import com.lk.memobar2.main.MainActivity
import com.lk.memobar2.main.Utils
import java.lang.StringBuilder

/**
 * Erstellt von Lena am 28/04/2019.
 */
object MemosNotification {

    private const val TAG = "MemosNotification"
    const val NOTIFICATION_ID: Int = 1001
    private const val CHANNEL_ID: String = "com.lk.memobar2.memonotification"
    private const val LAUNCH_ACTION: String = "com.lk.memobar2.launch"

    private lateinit var builder: Notification.Builder

    fun buildNotification(context: Context, memos: List<MemoEntity>): Notification {
        val notificationText =
            getNotificationStringFromList(memos)
        return buildNotification(context, notificationText)
    }

    fun getNotificationStringFromList(memos: List<MemoEntity>): String{
        val text = StringBuilder()
        for(memo in memos) {
            text.append(memo.content).append("\n")
        }
        return text.toString().trim('\n')
    }

    fun buildNotification(context: Context, text: String): Notification {
        createNotificationChannel(context)
        initialiseBuilder(context)
        setContent(text, context)
        return builder.build()
    }

    // TODO make Channel describtions localised
    private fun createNotificationChannel(context: Context){
        if(Utils.isBuildVersionGreaterThan(Build.VERSION_CODES.O)) {
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if(manager.getNotificationChannel(CHANNEL_ID) == null) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    "Memo notifications",
                    NotificationManager.IMPORTANCE_NONE
                )
                channel.description = "Show currently active memos"
                channel.setShowBadge(false)
                channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                manager.createNotificationChannel(channel)
            }
        }
    }

    private fun initialiseBuilder(context: Context){
        builder = if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            Notification.Builder(context, CHANNEL_ID)
        } else {
            Notification.Builder(context)
        }
    }

    private fun setContent(notificationText: String, context: Context){
        builder.setContentText(notificationText)
        builder.setShowWhen(false)
        builder.setSmallIcon(R.drawable.ic_notification)
        builder.style = Notification.BigTextStyle()
        setContentIntent(context)
    }

    private fun setContentIntent(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        val stack = TaskStackBuilder.create(context)
        stack.addParentStack(MainActivity::class.java)
        stack.addNextIntent(intent)
        val pi = stack.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        builder.setContentIntent(pi)
    }
}