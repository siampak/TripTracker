package com.example.tourmate.customdialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tourmate.R
import com.example.tourmate.adapters.MomentAdapter
import com.example.tourmate.model.MomentModel

class ShowMomentListDialog (val list: List<MomentModel>) : DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog{
        val layout = requireActivity().layoutInflater.inflate(R.layout.view_expense_layout, null)
        val recyclerView =layout.findViewById<RecyclerView>(R.id.expenseRVId)
        recyclerView.layoutManager= GridLayoutManager(requireActivity(),2)

        val adapter = MomentAdapter().apply {

            submitList(list)
        }
        recyclerView.adapter = adapter


        val builder = AlertDialog.Builder(requireActivity())

            .setTitle("My Moments") //expense dialog title name
            .setView(layout)
            .setNegativeButton("Close", null)

        return builder.create()

    }
}