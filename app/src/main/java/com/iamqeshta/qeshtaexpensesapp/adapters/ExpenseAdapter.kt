package com.iamqeshta.qeshtaexpensesapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iamqeshta.qeshtaexpensesapp.databinding.RvExpenseRowBinding
import com.iamqeshta.qeshtaexpensesapp.models.Expense
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ExpenseAdapter (var context: Context, var ExpensesList: ArrayList<Expense>) :
    RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    inner class ExpenseViewHolder(var binding: RvExpenseRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val binding = RvExpenseRowBinding.inflate(LayoutInflater.from(context), parent, false)
        return ExpenseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.binding.moneyTv.text = ExpensesList[position].money.toString()
        holder.binding.placeTv.text = ExpensesList[position].place
        holder.binding.dateTv.text = SimpleDateFormat("dd/MM/yyyy", Locale.US).format(ExpensesList[position].date.time)
    }

    override fun getItemCount(): Int {
        return ExpensesList.size
    }
}