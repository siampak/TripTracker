package com.example.tourmate.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tourmate.databinding.ExpenseRowItemBinding
import com.example.tourmate.model.ExpenseModel



class ExpenseAdapter : ListAdapter<ExpenseModel, ExpenseAdapter.ExpenseViewHolder>(ExpenseDiffUtil()){


    class ExpenseViewHolder(val binding: ExpenseRowItemBinding) : RecyclerView.ViewHolder(binding.root){


        fun bind(expenseModel: ExpenseModel) {
        binding.model = expenseModel


//            binding.rowExpenseTitleId.text = expenseModel.title
//            binding.rowExpenseAmountId.text = expenseModel.amount.toString()

        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
    val binding = ExpenseRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ExpenseViewHolder(binding)
    }

   override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {

      holder.bind(getItem(position))

    }
}

//from 'ExpenseDiffUtil()' to create class then two fun make.
class ExpenseDiffUtil : DiffUtil.ItemCallback<ExpenseModel>() {
    override fun areItemsTheSame(oldItem: ExpenseModel, newItem: ExpenseModel): Boolean {
        return oldItem.expenseId == newItem.expenseId
    }

    override fun areContentsTheSame(oldItem: ExpenseModel, newItem: ExpenseModel): Boolean {
        return oldItem == newItem
    }

}


//class ExpenseAdapter : ListAdapter<ExpenseModel, ExpenseViewHolder>(ExpenseViewHolder>(ExpenseDiffUtil())){
//
//
//    class ExpenseViewHolder : RecycleView.ViewHolder(binding.root)
//
//
//    fun bind(expenseModel: ExpenseModel) {
//        binding.model = expenseModel
//    }
//
//
//}
//
//fun onCreateViewHolder(parent: ViewGroup, ViewType: Int): ExpenseViewHolder {
//    val binding = ExpenseRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//    return ExpenseViewHolder(binding)
//}
//
//}
//
//Override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
//
//    holder.bind(getItem(position))
//
//}
//
//}