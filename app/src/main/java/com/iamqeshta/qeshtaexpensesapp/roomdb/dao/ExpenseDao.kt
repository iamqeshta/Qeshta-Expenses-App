package com.iamqeshta.qeshtaexpensesapp.roomdb.dao

import androidx.room.*
import com.iamqeshta.qeshtaexpensesapp.models.DateConverter
import com.iamqeshta.qeshtaexpensesapp.models.Expense
import java.util.*

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

    @Query("select * from Expense where uid =:uId and e_date =:eDate")
    @TypeConverters(DateConverter::class)
    fun getExpensesByDate(uId:Int, eDate: Date): MutableList<Expense>

    @Query("select * from Expense where uid =:uId order by eid desc limit 1")
    fun getLastExpense(uId:Int): Expense
}