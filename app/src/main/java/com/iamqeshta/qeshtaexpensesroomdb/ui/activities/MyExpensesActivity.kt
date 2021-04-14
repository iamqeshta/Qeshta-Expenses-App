package com.iamqeshta.qeshtaexpensesroomdb.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.iamqeshta.qeshtaexpensesroomdb.adapters.ExpenseAdapter
import com.iamqeshta.qeshtaexpensesroomdb.databinding.ActivityMyExpensesBinding
import com.iamqeshta.qeshtaexpensesroomdb.models.Expense
import java.util.*

class MyExpensesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyExpensesBinding
    private val expensesList = ArrayList<Expense>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMyExpensesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        val lm = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.ExpenseListRv.layoutManager = lm
        populateArray()
        val adapter = ExpenseAdapter(this, expensesList)
        binding.ExpenseListRv.adapter = adapter

        /*binding.fab.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog()
            bottomSheetDialog.show(supportFragmentManager, bottomSheetDialog.tag)
        }*/
    }

    fun populateArray(){
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

}