package com.lk.memobar2.database

import android.content.Context
import androidx.room.*

/**
 * Erstellt von Lena am 26/04/2019.
 */
@Database(entities = [MemoEntity::class], version = 1)
abstract class MemoDatabase: RoomDatabase() {

    abstract fun memoDao(): DAOMemos

    companion object {
        @Volatile
        private var INSTANCE: MemoDatabase? = null

        fun getInstance(context: Context): MemoDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                MemoDatabase::class.java, "notes.db")
                .build()

    }

}