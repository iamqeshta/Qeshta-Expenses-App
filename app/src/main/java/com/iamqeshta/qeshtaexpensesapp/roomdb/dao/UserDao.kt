package com.iamqeshta.qeshtaexpensesapp.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.iamqeshta.qeshtaexpensesapp.models.User

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("select * from User where u_email =:uEmail and u_password=:uPassword")
    fun getUser(uEmail: String, uPassword: String): User?
}