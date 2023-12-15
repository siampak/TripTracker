package com.example.tourmate.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tourmate.model.ExpenseModel
import com.example.tourmate.model.MomentModel
import com.example.tourmate.model.TourModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class TourRepository {
    val db = Firebase.firestore


    fun addTour(tourModel: TourModel){
       val docRef = db.collection(collection_tour).document()   //we can also use direct .add(tourModel) but here need id that's why we use set,document.
        tourModel.id =docRef.id
        docRef.set(tourModel)
            .addOnSuccessListener {
//                Toast.makeText(,"Successfully Added",Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{

            }

    }


    //Check Box update Status
    fun updateTourStatus(id:String, status: Boolean){

        val docRef = db.collection(collection_tour).document(id)
        docRef.update(mapOf("completed" to status))
            .addOnSuccessListener {

            }.addOnFailureListener {

            }

    }



    fun addExpense(expenseModel: ExpenseModel, tourId:String){

        val docRef = db.collection(collection_tour).document(tourId)//specific collection on tourId
            .collection(collection_expense)
            .document() // create blank document in collection expense
        expenseModel.expenseId = docRef.id
        docRef.set(expenseModel)
            .addOnSuccessListener {

            }
            .addOnFailureListener {

            }
    }


    fun addMoment(momentModel: MomentModel, tourId: String){

        val docRef =db.collection(collection_tour).document(tourId)
            .collection(collection_photos)
            .document()

        momentModel.momentId =docRef.id
        docRef.set(momentModel).addOnFailureListener {  }.addOnSuccessListener {  }

    }



    //retrieve data from firebase collection
    fun getToursByUser(userId:String) : LiveData<List<TourModel>> {
        val tourListLiveData = MutableLiveData<List< TourModel>>()
        db.collection(collection_tour)
            .whereEqualTo("userId",userId)  //first query then snapshot
            .addSnapshotListener { value, error ->

               if(error !=null) {
                   return@addSnapshotListener
               }

                val temp =ArrayList<TourModel>()
                for (doc in value!!){
                    temp.add(doc.toObject(TourModel::class.java))
                }
                tourListLiveData.postValue(temp)
            }
        return  tourListLiveData
    }



    //checked id match then error check or post value& convert object
    fun getTourById(tourId:String) : LiveData<TourModel>{
        val tourLiveData = MutableLiveData<TourModel>()

        db.collection(collection_tour).document(tourId)
            .addSnapshotListener { value, error ->

                if(error!= null) {
                    return@addSnapshotListener
                }

                tourLiveData.postValue(value!!.toObject(TourModel::class.java))
            }

        return  tourLiveData
    }



    //retrieve data from firebase collection
    fun getExpenses(tourId:String) : LiveData<List<ExpenseModel>> {
        val expenseListLiveData = MutableLiveData<List<ExpenseModel>>()

        db.collection(collection_tour)
            .document(tourId)
            .collection(collection_expense)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    return@addSnapshotListener
                }

                val temp = ArrayList<ExpenseModel>()
                for (doc in value!!) {
                    temp.add(doc.toObject(ExpenseModel::class.java))
                }
                expenseListLiveData.postValue(temp)

            }
        return expenseListLiveData
    }

    fun getMoments(tourId:String) : LiveData<List<MomentModel>>{
        val momentListLiveData = MutableLiveData<List<MomentModel>>()

        db.collection(collection_tour).document(tourId)
            .collection(collection_photos)
            .addSnapshotListener { value, error ->
                if(error != null){
                    return@addSnapshotListener
            }
                val temp = ArrayList<MomentModel>()
                for(doc in value!!) {
                    temp.add(doc.toObject(MomentModel::class.java))
                }
                momentListLiveData.postValue(temp)
            }
        return  momentListLiveData
    }

    companion object{
        const val TAG ="db_error"
        const val collection_tour ="My Tours"
        const val collection_expense ="My Expenses"
        const val collection_photos ="My Photos"

    }
}




