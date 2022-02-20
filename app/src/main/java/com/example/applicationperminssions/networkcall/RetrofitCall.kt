package com.example.applicationperminssions.networkcall

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCall {
    private const val Base_Url = "https://dhanraj2221.000webhostapp.com/"

    private fun getRetrofitApi(): Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(Base_Url).build()
    }

    val apiInterface: ApiInterface by lazy {
        getRetrofitApi().create(ApiInterface::class.java)
    }
}