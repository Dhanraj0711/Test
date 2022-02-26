package com.example.applicationperminssions.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.applicationperminssions.model.NoteModelItem
import com.example.applicationperminssions.repository.HomeRepository

class HomeViewModel : ViewModel() {

    //    private var repository: HomeRepository? = null
//    private lateinit var noteListData: MutableLiveData<NetworkResponse>
    var statusMessage: String? = null

    val repoListLive = MutableLiveData<List<NoteModelItem>>()
    fun fetch() {
        HomeRepository.getInstanc().getRepo { isSuccess, response, message ->
            if (isSuccess) {
                repoListLive.value = response
                statusMessage = "Success: $message"
            } else {
                statusMessage = "Error: $message"
            }
        }
    }

//    fun getData()= liveData(Dispatchers.IO) {
//            emit(Resource.loading(null))
//        try {
//            emit(Resource.success(data = repository?.getData()))
//        }catch (e:Exception){
//            emit(Resource.error(data = null, message = e.message?:"Error Occurred!"))
//        }
//    }

//    fun getNoteList() {
//        repository?.getAllNotes()
//        noteListData = repository!!.noteList
//    }

//    fun getAllNotes() {
//        RetrofitCall.apiInterface.getNoteList().enqueue(object : Callback<List<NoteModelItem>> {
//            override fun onResponse(call: Call<List<NoteModelItem>>, response: Response<List<NoteModelItem>>) {
//                val list = response.body()
//                noteListData?.postValue(list?.let { NetworkResponse.GetNoteList(it) })
//            }
//
//            override fun onFailure(call: Call<List<NoteModelItem>>, t: Throwable) {
//                noteListData?.postValue(NetworkResponse.Error(t.toString()))
//            }
//        })
//    }

//    fun getAllNotes() {
//        RetrofitCall.apiInterface.getNoteList().enqueue(object : Callback<NoteModel> {
//            override fun onResponse(call: Call<NoteModel>, response: Response<NoteModel>) {
//                val list = response.body()
//                noteListData.postValue(list?.let { NetworkResponse.GetNoteList(it) })
//            }
//
//            override fun onFailure(call: Call<NoteModel>, t: Throwable) {
//                noteListData.postValue(NetworkResponse.Error(t.toString()))
//                Log.e("ERROR :=>", "" + t.toString())
//            }
//        })
//    }

}