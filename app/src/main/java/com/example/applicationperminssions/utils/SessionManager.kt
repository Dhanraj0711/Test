package com.example.applicationperminssions.utils

import android.view.View
import androidx.lifecycle.MutableLiveData

class SessionManager {


    val email=MutableLiveData<String>()
    val password=MutableLiveData<String>()

    fun d(){
        email.value=""
        password.value=""
    }
    fun getPref(
        key: String
    ) {
    }

    fun clickDone(view: View){

    }
}