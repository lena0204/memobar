package com.lk.memobar2.database

import androidx.lifecycle.LiveData

/**
 * Erstellt von Lena am 26/04/2019.
 */
interface DataRepository {

    fun getMemos(): LiveData<List<MemoEntity>>

    fun insertMemo(memo: MemoEntity)

    fun updateMemo(memo: MemoEntity)

    fun deleteMemo(memo: MemoEntity)

}