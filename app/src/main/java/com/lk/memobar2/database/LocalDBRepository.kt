package com.lk.memobar2.database

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*

/**
 * Erstellt von Lena am 26/04/2019.
 */
class LocalDBRepository(application: Context): DataRepository {

    private val TAG = "MemoRepository"

    private val db = MemoDatabase.getInstance(application)
    private val dao = db.memoDao()
    private val memos: LiveData<List<MemoEntity>> = dao.selectAll()

    override fun getMemos(): LiveData<List<MemoEntity>> {
        Log.d(TAG, "Amount of memos: ${memos.value?.size}")
        return memos
    }

    override fun insertMemo(memo: MemoEntity) {
        GlobalScope.launch(Dispatchers.Default) {
            dao.insertMemo(memo)
            Log.d(TAG, "Inserted Memo")
        }
    }

    override fun updateMemo(memo: MemoEntity) {
        GlobalScope.launch(Dispatchers.Default) {
            dao.updateMemo(memo)
            Log.d(TAG, "Updated Memo")
        }
    }

    override fun deleteMemo(memo: MemoEntity) {
        GlobalScope.launch(Dispatchers.Default) {
            dao.deleteMemo(memo)
            Log.d(TAG, "Deleted Memo")
        }
    }

}