package com.lk.memobar2.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.transaction
import androidx.lifecycle.*
import com.lk.memobar2.R
import com.lk.memobar2.database.MemoEntity
import com.lk.memobar2.fragments.ListFragment
import com.lk.memobar2.notification.MemoNotificationManager
import com.lk.memobar2.notification.MemosNotification

class MainActivity : AppCompatActivity(), Observer<List<MemoEntity>> {

    private lateinit var viewModel: MemoViewModel
    private lateinit var notificationManager: MemoNotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        setContentView(R.layout.activity_main)

        initialiseNotificationHandling()
        changeToRecyclerList()
    }

    private fun initialiseNotificationHandling(){
        viewModel = ViewModelProviders.of(this).get(MemoViewModel::class.java)
        viewModel.observeMemos(this, this)
        notificationManager = MemoNotificationManager(application)
    }

    private fun changeToRecyclerList(){
        supportFragmentManager.transaction {
            replace(R.id.fl_main, ListFragment())
        }
    }

    override fun onChanged(update: List<MemoEntity>?) {
        if(update != null){
            notificationManager.handleMemosUpdate(update)
        }
    }

    override fun onStop() {
        super.onStop()
        var data = viewModel.getMemos()
        data = data.filter { memo -> memo.isActive }
        val textData = MemosNotification.getNotificationStringFromList(data)
        SharedPreferenceAccess.putString(Utils.PREF_KEY_NOTIFICATION, textData, applicationContext)
    }
}
