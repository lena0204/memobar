package com.lk.memobar2.database

import androidx.room.*
import java.io.Serializable

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

    companion object {
        const val MEMO_KEY = "memo"
    }

}