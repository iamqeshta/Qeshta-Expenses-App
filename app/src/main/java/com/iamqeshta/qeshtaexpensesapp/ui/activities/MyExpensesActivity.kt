package com.iamqeshta.qeshtaexpensesapp.ui.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.iamqeshta.qeshtaexpensesapp.adapters.ExpenseAdapter
import com.iamqeshta.qeshtaexpensesapp.databinding.ActivityMyExpensesBinding
import com.iamqeshta.qeshtaexpensesapp.models.Expense
import com.iamqeshta.qeshtaexpensesapp.roomdb.database.DatabaseClient
import com.iamqeshta.qeshtaexpensesapp.ui.fragments.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.*

class MyExpensesActivity : LocalizationActivity() {
    private lateinit var binding: ActivityMyExpensesBinding
    private lateinit var sharedPreferences: SharedPreferences
    private var expensesList: MutableList<Expense>? = null
    private lateinit var adapter: ExpenseAdapter
    private lateinit var bottomSheetDialog: FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMyExpensesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
        sharedPreferences = getSharedPreferences("SETTINGS_APP", MODE_PRIVATE)
        checkLoginUIDarkMode()

        binding.appBar.menuIcon.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        binding.appBar.searchIcon.setOnClickListener { getCalender() }

        getExpenses("firstTime")
        binding.swipeToRefresh.setOnRefreshListener { getExpenses("Refresh") }

        binding.addExpenseFAB.setOnClickListener {
            val bottomSheetDialog =
                BottomSheetDialog(null, object : BottomSheetDialog.MyExpensesListener {
                    override fun onExpensesAdded(expense: Expense) {
                        addExpense(expense)
                    }

                    override fun onExpensesUpdated(expense: Expense) {}
                })
            bottomSheetDialog.show(supportFragmentManager, bottomSheetDialog.tag)
        }
    }

    private fun getExpenses(action: String) {
        if (action == "firstTime") {
            expensesList = DatabaseClient.getInstance(this)!!.appDatabase.ExpenseDao()
                .getAllExpenses(sharedPreferences.getInt("U_ID", 0))
            val lm = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.expensesRv.layoutManager = lm
            bottomSheetDialog = supportFragmentManager
            adapter = ExpenseAdapter(this, expensesList!!, bottomSheetDialog)
            binding.expensesRv.adapter = adapter

        } else if (action == "Refresh") {
            expensesList!!.clear()
            expensesList!!.addAll(
                DatabaseClient.getInstance(this)!!.appDatabase.ExpenseDao()
                    .getAllExpenses(sharedPreferences.getInt("U_ID", 0))
            )
            adapter.notifyDataSetChanged()
            binding.swipeToRefresh.isRefreshing = false
        }
        checkEmptyStatus()
    }

    private fun addExpense(expense: Expense) {
        DatabaseClient.getInstance(this)!!.appDatabase.ExpenseDao().insertExpense(expense)
        expensesList!!.add(
            DatabaseClient.getInstance(this)!!.appDatabase.ExpenseDao()
                .getLastExpense(sharedPreferences.getInt("U_ID", 0))
        )
        adapter.notifyDataSetChanged()
        checkEmptyStatus()
    }

    private fun checkEmptyStatus() {
        if (expensesList!!.isEmpty()) {
            binding.linearEmptyStatus.visibility = View.VISIBLE
            binding.expensesRv.visibility = View.GONE
        } else {
            binding.linearEmptyStatus.visibility = View.GONE
            binding.expensesRv.visibility = View.VISIBLE
        }
    }

    private fun getCalender() {
        val c = Calendar.getInstance()
        val mYear = c[Calendar.YEAR]
        val mMonth = c[Calendar.MONTH]
        val mDay = c[Calendar.DAY_OF_MONTH]
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->
                expensesList!!.clear()
                expensesList!!.addAll(
                    DatabaseClient.getInstance(this)!!.appDatabase.ExpenseDao().getExpensesByDate(
                        sharedPreferences.getInt("U_ID", 0),
                        SimpleDateFormat(
                            "dd/MM/yyyy",
                            Locale.ENGLISH
                        ).parse(dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year)!!
                    )
                )
                adapter.notifyDataSetChanged()
                checkEmptyStatus()
            }, mYear, mMonth, mDay
        )
        datePickerDialog.show()
    }

    private fun checkLoginUIDarkMode() {
        val edit = sharedPreferences.edit()
        edit.putString("LOGIN", "Two")
        edit.apply()
    }

}