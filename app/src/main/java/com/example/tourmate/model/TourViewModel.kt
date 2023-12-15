package com.example.tourmate.model

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tourmate.repos.TourRepository
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import java.io.ByteArrayOutputStream

class TourViewModel : ViewModel(){
    private val repository = TourRepository()

    fun addTour(tourModel: TourModel){
        repository.addTour(tourModel)
    }


    //for checkBox function , here parameter  carry Id & status
    fun updateTourStatus(id: String, status: Boolean){
        repository.updateTourStatus(id,status)
    }


    fun addExpense(expenseModel: ExpenseModel, tourId: String){
        repository.addExpense(expenseModel, tourId)
    }


    fun getToursByUser(userId:String) =repository.getToursByUser(userId)

    fun getTourById(tourId: String) =repository.getTourById(tourId)

    fun getAllExpenses(tourId: String) =repository.getExpenses(tourId)

    fun getAllMoments(tourId: String) =repository.getMoments(tourId)

    fun getTotalExpense(list: List<ExpenseModel>) :Int{

        var total =0
        list.forEach {
            total +=it.amount!!
        }

        return total

    }

    fun uploadPhoto(bitmap: Bitmap,tourId: String,tourName:String){

        val imageName ="${tourId}_${System.currentTimeMillis()}.jpg" //image name type sample
        val photoRef = Firebase.storage.reference.child("$tourName/$imageName")  // create directory


        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos)  //quality compress
        val imageData = baos.toByteArray()

        val uploadTask = photoRef.putBytes(imageData)
        uploadTask.addOnSuccessListener {

        }.addOnFailureListener{

        }


        //picture show in my app and for that download url throw it

        val urlTask = uploadTask.continueWithTask{ task->

         if(!task.isSuccessful){
             task.exception?.let {
                 throw it
             }
         }
            photoRef.downloadUrl
        }.addOnCompleteListener{ task->
            if(task.isSuccessful){
                val downloadUri = task.result
                val momentModel =MomentModel(imageName = imageName, imageUrl = downloadUri.toString())

                repository.addMoment(momentModel, tourId)
            }
        }
    }
}
