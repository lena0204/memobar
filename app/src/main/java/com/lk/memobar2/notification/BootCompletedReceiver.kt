package com.lk.memobar2.notification

import android.content.*
import android.util.Log
import com.lk.memobar2.database.DatabaseRepository
import kotlinx.coroutines.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.preference.PreferenceManager
import com.lk.memobar2.database.MemoEntity
import com.lk.memobar2.main.SharedPreferenceAccess
import com.lk.memobar2.main.Utils
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


/**
 * Erstellt von Lena am 28/04/2019.
 */
class BootCompletedReceiver: BroadcastReceiver() {

    private val TAG = "BootCompletedReceiver"

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "received boot completed; context is : ${context.packageName}")
        val iAction = intent.action
        if(iAction != null && iAction == Intent.ACTION_BOOT_COMPLETED) {
            runBlocking {
                val memoList = SharedPreferenceAccess.readString(Utils.PREF_KEY_NOTIFICATION, context)
                MemoNotificationManager(context).handleMemosUpdate(memoList)
                Log.d(TAG, "Finished update.")
            }
        }
    }

    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(0)
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(update: T?) {
                if(update != null ){
                    Log.d(TAG, "A non-null update!")
                    data[0] = update
                    latch.countDown()
                    liveData.removeObserver(this)
                }
            }
        }
        liveData.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)

        return data[0] as T
    }
}