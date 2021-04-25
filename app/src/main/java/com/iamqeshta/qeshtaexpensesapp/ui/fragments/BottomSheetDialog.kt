package com.iamqeshta.qeshtaexpensesapp.ui.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.iamqeshta.qeshtaexpensesapp.R
import com.iamqeshta.qeshtaexpensesapp.databinding.FragmentBottomSheetDialogBinding
import com.iamqeshta.qeshtaexpensesapp.models.Expense
import java.text.SimpleDateFormat
import java.util.*


class BottomSheetDialog(
    private var expense: Expense? = null,
    private var expensesListener: MyExpensesListener
) :
    BottomSheetDialogFragment() {
    interface MyExpensesListener {
        fun onExpensesAdded(expense: Expense)
        fun onExpensesUpdated(expense: Expense)
    }

    private lateinit var bottomSheetDialogBinding: FragmentBottomSheetDialogBinding
    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = FragmentBottomSheetDialogBinding.inflate(inflater, container, false)
        bottomSheetDialogBinding = view
        sharedPreferences = activity!!.getSharedPreferences(
            "SETTINGS_APP",
            AppCompatActivity.MODE_PRIVATE
        )

        if (expense != null) {
            bottomSheetDialogBinding.placeEdt.setText(expense!!.ePlace)
            bottomSheetDialogBinding.dateEdt.setText(
                SimpleDateFormat(
                    "dd/MM/yyyy",
                    Locale.US
                ).format(expense!!.eDate!!)
            )
            bottomSheetDialogBinding.moneyEdt.setText(expense!!.eMoney.toString())
        } else
            bottomSheetDialogBinding.dateEdt.setText(Calendar.getInstance()[Calendar.DAY_OF_MONTH].toString() + "/" + (Calendar.getInstance()[Calendar.MONTH] + 1) + "/" + Calendar.getInstance()[Calendar.YEAR])

        bottomSheetDialogBinding.addBtn.setOnClickListener {
            val place = bottomSheetDialogBinding.placeEdt.text.toString()
            val date = bottomSheetDialogBinding.dateEdt.text.toString()
            val money = bottomSheetDialogBinding.moneyEdt.text.toString()

            when {
                place.isEmpty() -> bottomSheetDialogBinding.placeEdt.error =
                    getString(R.string.required)
                date.isEmpty() -> bottomSheetDialogBinding.dateEdt.error =
                    getString(R.string.required)
                money.isEmpty() -> bottomSheetDialogBinding.moneyEdt.error =
                    getString(R.string.required)
                else -> {
                    if (expense != null) {
                        expense!!.ePlace = place
                        expense!!.eDate = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(date)
                        expense!!.eMoney = money.toInt()
                        expensesListener.onExpensesUpdated(expense!!)
                    } else {
                        val expense = Expense()
                        expense.ePlace = place
                        expense.eDate = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(date)
                        expense.eMoney = money.toInt()
                        expense.uid = sharedPreferences.getInt("U_ID", 0)
                        expensesListener.onExpensesAdded(expense)
                    }
                    dismiss()
                }
            }
        }

        bottomSheetDialogBinding.dateEdt.setOnClickListener { getCalender() }

        bottomSheetDialogBinding.cancelBtn.setOnClickListener { dismiss() }
        return bottomSheetDialogBinding.root
    }

    @SuppressLint("SetTextI18n")
    private fun getCalender() {
        val c = Calendar.getInstance()
        val mYear = c[Calendar.YEAR]
        val mMonth = c[Calendar.MONTH]
        val mDay = c[Calendar.DAY_OF_MONTH]
        val datePickerDialog = DatePickerDialog(
            context!!,
            { _, year, monthOfYear, dayOfMonth ->
                bottomSheetDialogBinding.dateEdt.setText(
                    dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year
                )
            }, mYear, mMonth, mDay
        )
        datePickerDialog.show()
    }
}