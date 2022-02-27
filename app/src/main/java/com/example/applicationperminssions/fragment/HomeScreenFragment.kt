package com.example.applicationperminssions.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.applicationperminssions.adapter.MyAdapter
import com.example.applicationperminssions.R
import com.example.applicationperminssions.databinding.FragmentSplashScreenBinding
import com.example.applicationperminssions.viewmodel.HomeViewModel

class HomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentSplashScreenBinding
    private val homeViewModel: HomeViewModel by viewModels()
//    private val permision = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
//
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplashScreenBinding.inflate(layoutInflater, container, false)


        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
        }, 2000)

//        getNoteList()

//        CoroutineScope(Dispatchers.IO) {}


        Log.e(">>>>>>>>>>>>>>>>>>>", "" + homeViewModel.fetch().toString())
//        homeViewModel.fetch()
        homeViewModel.repoListLive.observe(requireActivity()) {
            if (it.isNotEmpty()) {

                val ad = MyAdapter(it)

//            binding.textView2.text=it.toString()
//                binding.rvNoteList.setHasFixedSize(true)
//                binding.rvNoteList.layoutManager = LinearLayoutManager(requireContext())
                binding.rvNoteList.adapter = ad

//                ad.itemClickListener.apply {
//                    Toast.makeText(requireContext(), ""+it, Toast.LENGTH_SHORT).show()
//                }

                Toast.makeText(requireContext(), "" + homeViewModel.statusMessage, Toast.LENGTH_SHORT).show()
                Log.e("Worked MSG", "" + homeViewModel.statusMessage)

//                ad.updateList(it)
                Log.e("Worked ", "" + it.toString())
            } else {
                Toast.makeText(requireContext(), "" + homeViewModel.statusMessage, Toast.LENGTH_SHORT).show()
                Log.e("Failed", "" + it.toString())
                Log.e("Failed MSG", "" + homeViewModel.statusMessage)

            }

        }

//        homeViewModel.getData().observe(requireActivity()) {
//            it.let { res ->
//
//                when (res.status) {
//                    Status.SUCCESS -> {
//                        Log.e("SUCCESS", "" + it.data.toString()+""+it.data?.size)
//                    }
//                    Status.ERROR -> {
//                        Log.e("ERROR", "" + it.message)
//                        Toast.makeText(requireContext(), "" + it.message, Toast.LENGTH_SHORT).show()
//                    }
//                    Status.LOADING -> {
//                        Log.e("LOADING", "" + it.message)
//                    }
//                }
//            }
//        }
        return binding.root
    }

//    private fun getNoteList() {
//
//        runBlocking {
//            Log.e("CAll ", "" + homeViewModel.getNoteList().toString())
//            homeViewModel.getAllNotes()
//            homeViewModel.noteListData.observe(requireActivity()) {
//
//                Log.e("NOTE LIST:=>", "" + it.toString())
//                when (it) {
//                    is NetworkResponse.Error -> {
//                        Log.e("ERROR :=>", "" + it.toString())
//                    }
//                    is NetworkResponse.ErrorResponse -> {
//                        Log.e("ERROR :=>", "" + it.toString())
//                    }
//                    is NetworkResponse.ErrorForbidden -> {
//                        Log.e("ERROR :=>", "" + it.toString())
//                    }
//                    is NetworkResponse.ErrorAuthentication -> {
//                        Log.e("ERROR :=>", "" + it.toString())
//                    }
//                    is NetworkResponse.GetNoteList -> {
////                        binding.rvNoteList.setHasFixedSize(true)
////                        binding.rvNoteList.layoutManager = LinearLayoutManager(requireContext())
////                    binding.rvNoteList.adapter = MyAdapter(it.list)
//                        Log.e("NOTE LIST:=>", "" + it.toString())
//                    }
//                    else -> {}
//                }
//            }
//        }
//    }
}