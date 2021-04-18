package com.iamqeshta.qeshtaexpensesapp.ui.activities

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.iamqeshta.qeshtaexpensesapp.R
import com.iamqeshta.qeshtaexpensesapp.adapters.ExpenseAdapter
import com.iamqeshta.qeshtaexpensesapp.databinding.ActivityMyExpensesBinding
import com.iamqeshta.qeshtaexpensesapp.models.Expense
import com.iamqeshta.qeshtaexpensesapp.ui.fragments.BottomSheetDialog
import java.util.*

class MyExpensesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyExpensesBinding
    private val expensesList = ArrayList<Expense>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMyExpensesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        binding.appBar.menuIcon.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        binding.appBar.searchIcon.setOnClickListener {
            getCalender()
        }

        val lm = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.ExpenseListRv.layoutManager = lm
        populateArray()
        val adapter = ExpenseAdapter(this, expensesList)
        binding.ExpenseListRv.adapter = adapter

        //checkEmptyStatus(expensesList)

        binding.fab.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog()
            bottomSheetDialog.show(supportFragmentManager, bottomSheetDialog.tag)
        }
    }

    private fun populateArray(){
        expensesList.add(Expense("Metro", Calendar.getInstance().time,100))
        expensesList.add(Expense("Restaurant", Calendar.getInstance().time,200))
        expensesList.add(Expense("Mate City", Calendar.getInstance().time,300))
        expensesList.add(Expense("Bakery", Calendar.getInstance().time,400))
        expensesList.add(Expense("Fees", Calendar.getInstance().time,500))
        expensesList.add(Expense("College Fees", Calendar.getInstance().time,600))
        expensesList.add(Expense("Books", Calendar.getInstance().time,700))
        expensesList.add(Expense("Shopping", Calendar.getInstance().time,800))
        expensesList.add(Expense("Hospital", Calendar.getInstance().time,900))
        expensesList.add(Expense("Clothes", Calendar.getInstance().time,1000))
    }

    private fun getCalender(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, { view, year, monthOfYear, dayOfMonth ->
            view.setBackgroundColor(getColor(R.color.primary_light))
            // Display Selected date in TextView
            //Toast.makeText(this, SimpleDateFormat("MM/dd/yyyy", Locale.US).format(c.time), Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "$dayOfMonth / $month / $year", Toast.LENGTH_SHORT).show()
        }, year, month, day).show()
    }

    private fun checkEmptyStatus(list: List<Expense>) {
        if (list.isEmpty()) {
            binding.linearEmptyStatus.visibility = View.VISIBLE
            binding.ExpenseListRv.visibility = View.GONE
        } else {
            binding.linearEmptyStatus.visibility = View.GONE
            binding.ExpenseListRv.visibility = View.VISIBLE
        }
    }

}