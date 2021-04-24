package com.iamqeshta.qeshtaexpensesapp.roomdb.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iamqeshta.qeshtaexpensesapp.models.User
import com.iamqeshta.qeshtaexpensesapp.roomdb.dao.UserDao

@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}