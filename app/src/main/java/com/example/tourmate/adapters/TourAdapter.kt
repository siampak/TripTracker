package com.example.tourmate.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tourmate.databinding.TourRowBinding
import com.example.tourmate.model.TourModel
import com.example.tourmate.model.TourViewModel
import com.example.tourmate.utils.details_screen
import com.example.tourmate.utils.tour_status_update
import java.lang.reflect.Type

class TourAdapter(val callback:(id: String, action: String, status: Boolean) -> Unit) : ListAdapter<TourModel, TourAdapter.TourViewHolder> (TourDiffcallback()) {

    private lateinit var binding: TourRowBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourViewHolder {
        binding =TourRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TourViewHolder(binding)
    }


    override fun onBindViewHolder(holder: TourViewHolder, position: Int) {
        //here both binding are calling together

        holder.bind(getItem(position))
        binding.tourRowitemId.setOnClickListener{
           callback(getItem(position).id!!, details_screen,false)
        }
        binding.rowCompletedId.setOnClickListener {
            callback(getItem(position).id!!, tour_status_update,!getItem(position).isCompleted)
        }


    }






    // this class generated row  call it ViewHolder & pass binding xml=TourRowBinding(tour_row)
    class TourViewHolder(val binding: TourRowBinding ):RecyclerView.ViewHolder(binding.root) {

        fun bind(tourModel: TourModel) {
            binding.tourModel  =tourModel

//            binding.rowTitleId.text = tourModel.title
//            binding.rowDateId.text = tourModel.createOn.toDate().toString()
//            binding.rowCompletedId.isChecked =tourModel.isCompleted

        }

    }



    class TourDiffcallback: DiffUtil.ItemCallback<TourModel>(){
        override fun areItemsTheSame(oldItem: TourModel, newItem: TourModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TourModel, newItem: TourModel): Boolean {
            return oldItem == newItem
        }
    }
}