package com.example.tourmate.logins

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel: ViewModel() {

    enum class AuthenticationStatus {
        AUTENTETICATED,UNAUTHENTICATE
    }

    private  val auth = FirebaseAuth.getInstance()
    var user = auth.currentUser

    var errMsgLiveData: MutableLiveData<String> = MutableLiveData()
    val authStatusLiveData: MutableLiveData<AuthenticationStatus> = MutableLiveData()


    init {
        user?.let {
            authStatusLiveData.postValue(AuthenticationStatus.AUTENTETICATED)
        } ?:authStatusLiveData.postValue(AuthenticationStatus.UNAUTHENTICATE)
    }



    fun login(email:String, password:String){

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                if (it.isSuccessful){
                    authStatusLiveData.postValue((AuthenticationStatus.AUTENTETICATED))
                }
            }.addOnFailureListener{
                errMsgLiveData.value =it.localizedMessage
            }
    }


    //register check email & password
    fun register(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                if (it.isSuccessful){
                    authStatusLiveData.postValue((AuthenticationStatus.AUTENTETICATED))
                }
            }.addOnFailureListener{
                errMsgLiveData.value =it.localizedMessage
            }
    }




    fun  isUserValid() = false


    fun sendVerificationMail(){


    }

    fun logout(){

    }
}