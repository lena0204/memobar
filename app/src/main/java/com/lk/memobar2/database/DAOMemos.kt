package com.lk.memobar2.database

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Erstellt von Lena am 26/04/2019.
 */
@Dao
interface DAOMemos {

    @Insert
    fun insertMemo(memo: MemoEntity): Long

    @Delete
    fun deleteMemo(memo: MemoEntity)

    @Update
    fun updateMemo(memo: MemoEntity)

    @Query("DELETE FROM memos")
    fun deleteAll()

    @Query("SELECT * FROM memos ORDER BY importance DESC, lastUpdated DESC")
    fun selectAll(): LiveData<List<MemoEntity>>

}