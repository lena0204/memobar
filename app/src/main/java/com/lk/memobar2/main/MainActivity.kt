package com.lk.memobar2.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.transaction
import com.lk.memobar2.R
import com.lk.memobar2.fragments.ListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeToRecyclerList()
    }

    private fun changeToRecyclerList(){
        supportFragmentManager.transaction {
            replace(R.id.fl_main, ListFragment())
        }
    }

    companion object {
        const val DIALOG_TITLE_RESOURCE = "dialogTitleResource"
    }
}