package com.example.applicationperminssions.networkcall

import com.example.applicationperminssions.model.NoteModel
import com.example.applicationperminssions.model.NoteModelItem
import com.example.applicationperminssions.model.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @GET("allnote.php")
    fun getNoteList(): Call<List<NoteModelItem>>

    @GET("allnote.php")
    suspend fun getNoteLists(): NoteModel

    @FormUrlEncoded
    @POST("login.php")
     fun login(@Field("user_name") email:String,@Field("user_password") password:String): Call<UserData>
}
