package com.example.applicationperminssions.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.applicationperminssions.model.NoteModel
import com.example.applicationperminssions.networkcall.NetworkResponse
import com.example.applicationperminssions.networkcall.RetrofitCall
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository {

//    suspend fun getData() = apiInterface.getNoteLists()


    lateinit var noteList: MutableLiveData<NetworkResponse>
//    fun getAllNotes() {
//        RetrofitCall.apiInterface.getNoteList().enqueue(object : Callback<List<NoteModelItem>> {
//            override fun onResponse(call: Call<List<NoteModelItem>>, response: Response<List<NoteModelItem>>) {
//                val list = response.body()
//                noteList.postValue(list?.let { NetworkResponse.GetNoteList(it) })
//            }
//
//            override fun onFailure(call: Call<List<NoteModelItem>>, t: Throwable) {
//                noteList.postValue(NetworkResponse.Error(t.toString()))
//            }
//        })
//    }

    fun getRepo(onResult: (isSuccess: Boolean, response: NoteModel, message: String) -> Unit) {
        RetrofitCall.apiInterface.getNoteList().enqueue(object : Callback<NoteModel> {
            override fun onResponse(call: Call<NoteModel>, response: Response<NoteModel>) {
                val list = response.body()
                if (response.isSuccessful)
                    list?.let { onResult(true, it,  "Success :::::::::::::"+response.message()) }
                else
                    list?.let { onResult(false, it, "" + response.errorBody()) }
                Log.e("NOTE LIST:=>", "" + response.body().toString())
            }

            override fun onFailure(call: Call<NoteModel>, t: Throwable) {
                Log.e("ERROR :=>", "" + t.toString())
            }
        })
    }

    fun getAllNotes() {
        RetrofitCall.apiInterface.getNoteList().enqueue(object : Callback<NoteModel> {
            override fun onResponse(call: Call<NoteModel>, response: Response<NoteModel>) {
                val list = response.body()
                noteList.postValue(list?.let { NetworkResponse.GetNoteList(it) })
                Log.e("NOTE LIST:=>", "" + response.body().toString())
            }

            override fun onFailure(call: Call<NoteModel>, t: Throwable) {
                Log.e("ERROR :=>", "" + t.toString())
            }
        })
    }


    companion object {
        private var Instance: HomeRepository? = null
        fun getInstance() = Instance ?: HomeRepository().also {
            Instance = it
        }

        private var Instanc: HomeRepository? = null
        fun getInstanc() = Instanc ?: synchronized(this) {
            Instanc ?: HomeRepository().also {
                Instance = it
            }
        }
    }
}