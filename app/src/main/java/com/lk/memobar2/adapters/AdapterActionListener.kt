package com.lk.memobar2.adapters

/**
 * Erstellt von Lena am 26/04/2019.
 */
interface AdapterActionListener {

    fun changeActiveState(memoId: Int)
    fun changeImportance(memoId: Int)
    fun editMemo(memoId: Int)
    fun storeLongClickId(memoId: Int)

}