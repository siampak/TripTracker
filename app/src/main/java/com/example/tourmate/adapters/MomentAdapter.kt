package com.example.tourmate.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.tourmate.databinding.MomentRowItemBinding
import com.example.tourmate.model.MomentModel
import java.lang.reflect.Type
import java.text.FieldPosition


class MomentAdapter: ListAdapter<MomentModel, MomentAdapter.MomentViewHolder>(MomentDiffCallback()) {

    class MomentViewHolder(val binding: MomentRowItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(momentModel: MomentModel){
            Glide.with(binding.root.context).load(momentModel.imageUrl)
                .into(binding.momentRowImageId)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MomentViewHolder{
        val binding = MomentRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return  MomentViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MomentViewHolder, position: Int){
        val model = getItem(position)
        holder.bind(model)
    }

}

class MomentDiffCallback : DiffUtil.ItemCallback<MomentModel>() {
    override fun areItemsTheSame(oldItem: MomentModel, newItem: MomentModel): Boolean {
        return oldItem.momentId == newItem.momentId
    }

    override fun areContentsTheSame(oldItem: MomentModel, newItem: MomentModel): Boolean {
        return oldItem.momentId == newItem.momentId
    }

}
