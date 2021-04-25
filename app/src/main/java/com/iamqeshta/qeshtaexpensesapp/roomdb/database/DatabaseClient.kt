package com.iamqeshta.qeshtaexpensesapp.roomdb.database

import android.content.Context
import androidx.room.Room

class DatabaseClient(mContext: Context) {
    val appDatabase: AppDatabase =
        Room.databaseBuilder(mContext, AppDatabase::class.java, "Qeshta_Expenses")
            .allowMainThreadQueries().build()

    companion object {
        private var mInstance: DatabaseClient? = null

        @Synchronized
        fun getInstance(context: Context?): DatabaseClient? {
            if (mInstance == null) {
                mInstance = DatabaseClient(context!!)
            }
            return mInstance!!
        }
    }
}