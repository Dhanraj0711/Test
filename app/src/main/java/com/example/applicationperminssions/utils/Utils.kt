package com.example.applicationperminssions.utils

import android.app.Activity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Utils {

    fun <T>RecyclerView.setListAdapter(activity: Activity, aa:T)
    {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(activity)
//        adapter=aa
    }
}