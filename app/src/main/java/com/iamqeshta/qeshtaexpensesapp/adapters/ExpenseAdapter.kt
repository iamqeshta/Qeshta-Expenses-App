package com.iamqeshta.qeshtaexpensesapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.iamqeshta.qeshtaexpensesapp.R
import com.iamqeshta.qeshtaexpensesapp.databinding.RvExpenseRowBinding
import com.iamqeshta.qeshtaexpensesapp.models.Expense
import com.iamqeshta.qeshtaexpensesapp.roomdb.database.DatabaseClient
import com.iamqeshta.qeshtaexpensesapp.ui.fragments.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.*

class ExpenseAdapter(
    private var context: Context,
    private var ExpensesList: MutableList<Expense>,
    private var supportFragmentManager: FragmentManager
) :
    RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    inner class ExpenseViewHolder(var binding: RvExpenseRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val binding = RvExpenseRowBinding.inflate(LayoutInflater.from(context), parent, false)
        return ExpenseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.binding.placeTv.text = ExpensesList[position].ePlace
        holder.binding.dateTv.text =
            SimpleDateFormat("dd/MM/yyyy", Locale.US).format(ExpensesList[position].eDate!!)
        holder.binding.moneyTv.text = ExpensesList[position].eMoney.toString()

        holder.binding.edit.setOnClickListener { updateExpense(ExpensesList[position], position) }
        holder.binding.delete.setOnClickListener { deleteExpense(ExpensesList[position]) }
    }

    override fun getItemCount(): Int {
        return ExpensesList.size
    }

    private fun updateExpense(expense: Expense, position: Int) {
        val bottomSheetDialog =
            BottomSheetDialog(expense, object : BottomSheetDialog.MyExpensesListener {
                override fun onExpensesAdded(expense: Expense) {}
                override fun onExpensesUpdated(expense: Expense) {
                    DatabaseClient.getInstance(context)!!.appDatabase.ExpenseDao()
                        .updateExpense(expense)
                    ExpensesList[position] =
                        DatabaseClient.getInstance(context)!!.appDatabase.ExpenseDao()
                            .getExpenses(expense.eid)
                    notifyDataSetChanged()
                    Toast.makeText(context, R.string.update_successfully, Toast.LENGTH_SHORT).show()
                }
            })
        bottomSheetDialog.show(supportFragmentManager, bottomSheetDialog.tag)
    }

    private fun deleteExpense(expense: Expense) {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle(context.resources.getString(R.string.delete_expense))
        dialog.setMessage(context.resources.getString(R.string.confirm_delete_expense))
        dialog.setIcon(R.drawable.ic_delete)
        dialog.setPositiveButton(context.resources.getString(R.string.yes)) { _, _ ->
            DatabaseClient.getInstance(context)!!.appDatabase.ExpenseDao().deleteExpense(expense)
            ExpensesList.remove(expense)
            notifyDataSetChanged()
            Toast.makeText(context, R.string.delete_expense_successfully, Toast.LENGTH_SHORT).show()
        }
        dialog.setNegativeButton(context.resources.getString(R.string.no)) { _, _ -> }
        val alertDialog: AlertDialog = dialog.create()
        alertDialog.show()
    }
}