package com.iamqeshta.qeshtaexpensesapp.roomdb.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iamqeshta.qeshtaexpensesapp.models.Expense
import com.iamqeshta.qeshtaexpensesapp.models.User
import com.iamqeshta.qeshtaexpensesapp.roomdb.dao.ExpenseDao
import com.iamqeshta.qeshtaexpensesapp.roomdb.dao.UserDao

@Database(entities = [User::class, Expense::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun ExpenseDao(): ExpenseDao
}