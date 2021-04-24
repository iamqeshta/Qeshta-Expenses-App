package com.iamqeshta.qeshtaexpensesapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User {
    @PrimaryKey(autoGenerate = true)
    var uid = 0

    @ColumnInfo(name = "u_name")
    var uName: String? = null

    @ColumnInfo(name = "u_mobile")
    var uMobile: String? = null

    @ColumnInfo(name = "u_email")
    var uEmail: String? = null

    @ColumnInfo(name = "u_password")
    var uPassword: String? = null
}