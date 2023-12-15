package com.example.tourmate.customdialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tourmate.R
import com.example.tourmate.adapters.ExpenseAdapter
import com.example.tourmate.model.ExpenseModel

class ShowExpenseListDialog (val list: List<ExpenseModel>) : DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val layout = requireActivity().layoutInflater.inflate(R.layout.view_expense_layout, null)
        val recyclerView =layout.findViewById<RecyclerView>(R.id.expenseRVId)
        recyclerView.layoutManager= LinearLayoutManager(requireActivity())

        val adapter = ExpenseAdapter().apply {

            submitList(list)
        }
        recyclerView.adapter = adapter


        val builder = AlertDialog.Builder(requireActivity())

            .setTitle("Expense List") //expense dialog title name
            .setView(layout)
            .setNegativeButton("Close", null)



        return builder.create()
    }
}