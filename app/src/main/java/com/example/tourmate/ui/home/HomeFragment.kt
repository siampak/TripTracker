package com.example.tourmate.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tourmate.R
import com.example.tourmate.adapters.TourAdapter
import com.example.tourmate.databinding.FragmentHomeBinding
import com.example.tourmate.logins.LoginViewModel
import com.example.tourmate.model.TourViewModel
import com.example.tourmate.utils.details_screen
import com.example.tourmate.utils.tour_status_update


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding  //Data binding

    private val tourViewModel:TourViewModel by viewModels()
    private  val loginViewModel:LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container,false)

        //retrieve data
        val adapter =TourAdapter{id, action, status ->

            when(action){
                details_screen -> {

                    val navAction = HomeFragmentDirections.tourDetailsAction()
                    navAction.tourId = id
                    findNavController().navigate(navAction)
                }
                tour_status_update ->tourViewModel.updateTourStatus(id,status) //for check box update & here call from tourViewModel
            }

        }

        binding.tourRecyclerViewId.layoutManager = LinearLayoutManager(activity)
        binding.tourRecyclerViewId.adapter =adapter


        //retrieve data
        tourViewModel.getToursByUser(loginViewModel.user!!.uid)
            .observe(viewLifecycleOwner) {

                adapter.submitList(it)
            }

        binding.fabId.setOnClickListener{
            findNavController().navigate(R.id.new_tour_action)
        }


        return binding.root
    }
}
//        val homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)
//
//        _binding = FragmentHomeBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
////        val textView: TextView = binding.textHome
////        homeViewModel.text.observe(viewLifecycleOwner) {
////            textView.text = it
//        }

//    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}