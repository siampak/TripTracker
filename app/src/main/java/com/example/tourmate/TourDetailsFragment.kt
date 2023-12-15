package com.example.tourmate

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.tourmate.customdialogs.AddExpenseDialog
import com.example.tourmate.customdialogs.ShowExpenseListDialog
import com.example.tourmate.customdialogs.ShowMomentListDialog
import com.example.tourmate.databinding.FragmentTourDetailsBinding
import com.example.tourmate.model.ExpenseModel
import com.example.tourmate.model.MomentModel
import com.example.tourmate.model.TourViewModel

class TourDetailsFragment : Fragment() {
    private lateinit var  binding: FragmentTourDetailsBinding
    private val tourViewModel: TourViewModel by viewModels()

    private var tourId: String? = null
    private var tourName: String? = null
    private var expenseList = listOf<ExpenseModel>()
    private var momentList = listOf<MomentModel>() //for total image



    //for camera click then back my app (back Button)
    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode == Activity.RESULT_OK){

            val bmp = result.data?.extras?.get("data") as Bitmap   //raw data(image data) = bmp

            tourViewModel.uploadPhoto(bitmap = bmp, tourId=tourId!!,tourName= tourName!!)  //call upload method and input parameter
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTourDetailsBinding.inflate(inflater, container, false)

        val args: TourDetailsFragmentArgs by navArgs()
        tourId = args.tourId



        //Tour Model = check all tour id for match and show it
        tourId?.let {

            tourViewModel.getTourById(it).observe(viewLifecycleOwner) {

                binding.tourModel = it

                //upload photo on fire base
                tourName = it.title
            }
        }



            //Expense Model = check all tour id for match and show it
            tourId?.let {
                tourViewModel.getAllExpenses(it).observe(viewLifecycleOwner) {

                    expenseList = it
                    val totalExpense = tourViewModel.getTotalExpense(it)

                    binding.totalExpense =totalExpense
                }
            }


        //total moment --bring moment by the tour id
        tourId?.let {
            tourViewModel.getAllMoments(it).observe(viewLifecycleOwner) {momentList ->

                this.momentList = momentList
                binding.totalMoments = momentList.size  //here total moments is binding varriable Name
            }
        }

            //add expense data on dialog
            binding.detailsExpenseAddBtnId.setOnClickListener{
                tourId?.let {
                    AddExpenseDialog{ expenseModel ->

                        tourViewModel.addExpense(expenseModel, it)  //now ViewModel call for retrieve data
                    }.show(childFragmentManager, "add_expense")
                }
            }



        binding.detailsExpenseDetailsBtnId.setOnClickListener{
            ShowExpenseListDialog(expenseList).show(childFragmentManager, "expense_list")
        }



        //camera BTN

        binding.detailsMomentsCameraBtnId.setOnClickListener {

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)


            try {
                cameraLauncher.launch(intent)
            } catch (e: ActivityNotFoundException) {

            }
        }

        binding.detailsMomentsGalleryBtnId.setOnClickListener{

            ShowMomentListDialog(momentList).show(childFragmentManager,"moments")
        }

        return binding.root
        }


}


