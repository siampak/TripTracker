package com.example.tourmate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tourmate.databinding.FragmentNewTourBinding
import com.example.tourmate.logins.LoginViewModel
import com.example.tourmate.model.TourModel
import com.example.tourmate.model.TourViewModel
import com.example.tourmate.utils.getFormattedDateTime


class NewTourFragment : Fragment() {


    private lateinit var  binding: FragmentNewTourBinding //Data binding
    private val loginViewModel:LoginViewModel by viewModels()
    private val tourViewModel:TourViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewTourBinding.inflate(inflater,container,false)

        binding.createBtnId.setOnClickListener {
           val title  = binding.nameTourId.text.toString()
            val destination =binding.placeTxtId.text.toString()
            val budget =binding.budgetTxtId.text.toString().toInt()  //convert Integer

            //check Edit text empty or not?
//            if (title.isEmpty()){
//                binding.nameTourId.error = "please provide a tour name"
//                return@setOnClickListener
//            }else if(destination.isEmpty()){
//
//                binding.placeTxtId.error = "please provide a place name"
//                return@setOnClickListener
//            }else if (budget == null){
//
//                binding.budgetTxtId.error = "please provide an amount"
//            }


            val tourModel = TourModel(
                userId = loginViewModel.user?.uid,
                title = title,
                destination = destination,
                budget = budget
            )
            tourViewModel.addTour(tourModel)

            findNavController().popBackStack()  //backPage = here,Home fragment(sobcheye upore je stack ta(newTourFragment royeche sheta pop hbe)

        }
        return binding.root
    }


}

