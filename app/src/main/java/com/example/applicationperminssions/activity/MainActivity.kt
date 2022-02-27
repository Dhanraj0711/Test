package com.example.applicationperminssions.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.applicationperminssions.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var result: ActivityResultLauncher<Array<String>>

    private var isRead = false
    private var isLocation = false
    private var isRecord = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkPermission()
        Log.e("onCreate", "onCreate")
    }

    private fun checkPermission() {
        result = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            isRead = permissions[Manifest.permission.READ_EXTERNAL_STORAGE] ?: isRead
            isLocation = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: isLocation
            isRecord = permissions[Manifest.permission.RECORD_AUDIO] ?: isRecord
        }
        request()

    }

    override fun onStop() {
        super.onStop()
        Log.e("onStop", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "onDestroy")
    }

    override fun onPause() {
        super.onPause()
        Log.e("onPause", "onPause")
    }

    override fun onStart() {
        super.onStart()
        isRead = false
        isLocation = false

        isRecord = false
        checkPermission()
        Log.e("onStart", "onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.e("onResume", "onResume")
    }

    private fun request() {
        isRead = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) ==

                PackageManager.PERMISSION_GRANTED
        isLocation = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        isRecord = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED

        val permissionReq: MutableList<String> = ArrayList()

        if (!isRead) {
            permissionReq.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if (!isLocation) {
            permissionReq.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (!isRecord) {
            permissionReq.add(Manifest.permission.RECORD_AUDIO)
        }

        if (permissionReq.isNotEmpty()) {
            result.launch(permissionReq.toTypedArray())
        }
    }
}