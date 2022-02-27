package com.example.applicationperminssions.networkcall

import com.example.applicationperminssions.utils.Status
import com.example.applicationperminssions.model.NoteModelItem

sealed class NetworkResponse {
    data class Error(val message: String) : NetworkResponse()
    data class ErrorForbidden(val message: String) : NetworkResponse()
    data class ErrorAuthentication(val message: String) : NetworkResponse()
    data class ErrorResponse(val message: String) : NetworkResponse()
    data class Suc(val suc: Success) : NetworkResponse()
    class GetNoteList(val list: List<NoteModelItem>) : NetworkResponse()
    sealed class Success {
        class GetNoteList(val list: List<NoteModelItem>) : Success()
    }

    data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
        companion object {
            fun <T> success(data: T): Resource<T> = Resource(status = Status.SUCCESS, data = data, message = null)
            fun <T> error(data: T?, message: String?): Resource<T> = Resource(status = Status.ERROR, data = data, message = message)
            fun <T> loading(data: T?): Resource<T> = Resource(status = Status.LOADING, data = data, message = null)
        }
    }

}