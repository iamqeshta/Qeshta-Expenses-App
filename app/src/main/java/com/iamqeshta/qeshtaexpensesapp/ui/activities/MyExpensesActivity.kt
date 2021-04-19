package com.iamqeshta.qeshtaexpensesapp.ui.activities

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.akexorcist.localizationactivity.core.LanguageSetting.setLanguage
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.iamqeshta.qeshtaexpensesapp.R
import com.iamqeshta.qeshtaexpensesapp.adapters.ExpenseAdapter
import com.iamqeshta.qeshtaexpensesapp.databinding.ActivityMyExpensesBinding
import com.iamqeshta.qeshtaexpensesapp.models.Expense
import com.iamqeshta.qeshtaexpensesapp.ui.fragments.BottomSheetDialog
import java.util.*

class MyExpensesActivity : LocalizationActivity() {
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
        binding.expensesRv.layoutManager = lm
        populateArray()
        val adapter = ExpenseAdapter(this, expensesList)
        binding.expensesRv.adapter = adapter

        //checkEmptyStatus(expensesList)

        binding.fab.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog()
            bottomSheetDialog.show(supportFragmentManager, bottomSheetDialog.tag)
        }

    }

    private fun populateArray(){
        for (i in 1..10){
            expensesList.add(Expense(resources.getString(R.string.example_place), Calendar.getInstance().time,100))
        }
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
            binding.expensesRv.visibility = View.GONE
        } else {
            binding.linearEmptyStatus.visibility = View.GONE
            binding.expensesRv.visibility = View.VISIBLE
        }
    }

}