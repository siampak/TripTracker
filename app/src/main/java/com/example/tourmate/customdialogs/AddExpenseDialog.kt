package com.example.tourmate.customdialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.example.tourmate.R
import com.example.tourmate.model.ExpenseModel

class AddExpenseDialog (val callback: (ExpenseModel) -> Unit) : DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val layout = requireActivity().layoutInflater.inflate(R.layout.add_expense_layout, null)
        val builder = AlertDialog.Builder(requireActivity())
            .setTitle("Add Expense")//This is dialog Name
            .setView(layout)
            .setPositiveButton("Save") {dailog, which ->
                val titleET: EditText = layout.findViewById(R.id.addExpenseTitleId)
                val amountET: EditText = layout.findViewById(R.id.addExpenseAmountId)


                //PLZ validate fields
                val expenseModel =ExpenseModel(
                    title = titleET.text.toString(),
                    amount = amountET.text.toString().toInt()

                )
                callback(expenseModel)
            }

            .setNegativeButton("Cancel", null)



        return builder.create()

    }
}
