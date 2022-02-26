package com.example.applicationperminssions.networkcall

import com.example.applicationperminssions.model.NoteModel
import com.example.applicationperminssions.model.NoteModelItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET

interface ApiInterface {

    @GET("allnote.php")
    fun getNoteList(): Call<List<NoteModelItem>>

    @GET("allnote.php")
    suspend fun getNoteLists(): NoteModel

}
