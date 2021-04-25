package com.iamqeshta.qeshtaexpensesapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.*

@Entity
class Expense {
    @PrimaryKey(autoGenerate = true)
    var eid = 0

    @ColumnInfo(name = "e_place")
    var ePlace: String? = null

    @ColumnInfo(name = "e_date")
    @TypeConverters(DateConverter::class)
    var eDate: Date? = null

    @ColumnInfo(name = "e_money")
    var eMoney = 0

    @ColumnInfo(name = "uid")
    var uid: Int? = null
}
