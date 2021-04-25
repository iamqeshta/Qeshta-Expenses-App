package com.iamqeshta.qeshtaexpensesapp.roomdb.dao

import androidx.room.*
import com.iamqeshta.qeshtaexpensesapp.models.Expense

@Dao
interface ExpenseDao {
    @Insert
    fun insertExpense(expense: Expense)

    @Update
    fun updateExpense(expense: Expense)

    @Delete
    fun deleteExpense(expense: Expense)

    @Query("select * from Expense where uid =:uId")
    fun getAllExpenses(uId:Int): MutableList<Expense>

    @Query("select * from Expense where eid =:eId")
    fun getExpenses(eId:Int): Expense

    @Query("select * from Expense where uid =:uId order by eid desc limit 1")
    fun getLastExpenses(uId:Int): Expense
}