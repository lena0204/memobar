package com.lk.memobar2.database

import android.text.format.DateFormat
import android.util.Log
import androidx.room.*
import java.io.Serializable
import java.util.*

/**
 * Erstellt von Lena am 26/04/2019.
 */
@Entity(tableName = "memos")
class MemoEntity : Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "memo_id")
    var id: Int = 0

    @ColumnInfo(name = "content")
    var content: String = ""

    @ColumnInfo(name = "isActive")
    var isActive: Boolean = false

    @ColumnInfo(name = "lastUpdated")
    var lastUpdated: String = ""

    override fun toString(): String {
        return "{id: $id, content: \"$content\", isActive: $isActive, lastUpdated: $lastUpdated}"
    }

    fun setCurrentTimeStamp(){
        lastUpdated = DateFormat.format("yyyy/MM/dd HH:mm:ss", Date().time).toString()
    }
}