package com.example.applicationperminssions.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.applicationperminssions.model.UserData
import com.example.applicationperminssions.networkcall.NetworkResponse
import com.example.applicationperminssions.networkcall.RetrofitCall
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel : ViewModel() {

    var emailAddress = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    var userMutableLiveData: MutableLiveData<UserData>? = null


    fun getUser(): MutableLiveData<UserData>? {
        if (userMutableLiveData == null) {
            userMutableLiveData = MutableLiveData()
        }
        return userMutableLiveData
    }


    fun userLogin() {
        RetrofitCall.apiInterface.login(emailAddress.value!!, password.value!!)
            .enqueue(object : Callback<UserData> {
                override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                    if (response.isSuccessful) {
                        if (response.body()?.result_code == 1) {
                            userMutableLiveData?.value = response.body()
                            emailAddress.value = ""
                            password.value = ""

                            val r = ""
//                            NetworkResponse.Resource.success(response.body()!!)
                        }
                    }
                }

                override fun onFailure(call: Call<UserData>, t: Throwable) {
                    NetworkResponse.Resource.error(call, t.toString())
                }
            })
    }

}