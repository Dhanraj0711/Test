package com.example.applicationperminssions.fragment

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.applicationperminssions.R
import com.example.applicationperminssions.databinding.FragmentLoginBinding
import com.example.applicationperminssions.model.UserData
import com.example.applicationperminssions.networkcall.RetrofitCall
import com.example.applicationperminssions.viewmodel.LoginViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment(R.layout.fragment_login) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()
    val res: String = ""

    private fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        binding.loginModel = loginViewModel

        binding.c = this
        binding.lifecycleOwner = this

        loginViewModel.getUser()?.observe(requireActivity()) {
            binding.etEmail.text = loginViewModel.emailAddress.toString().toEditable()
            binding.etPassword.text = loginViewModel.password.toString().toEditable()

            Log.e("getUser", "" + it)
        }

        loginViewModel.userMutableLiveData?.observe(requireActivity()) {
            Log.e("UserData", "" + it)
        }

        return binding.root
    }

    fun userLogin() {
        Toast.makeText(requireContext(), "call", Toast.LENGTH_SHORT).show()

        RetrofitCall.apiInterface.login(
            binding.etEmail.text.toString().trim(),
            binding.etPassword.text.toString().trim()
        )
            .enqueue(object : Callback<UserData> {
                override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                    if (response.isSuccessful) {
                        if (response.body()?.result_code == 1) {
                            val userData = UserData(
                                id = response.body()!!.id,
                                name = response.body()!!.name,
                                password = response.body()!!.id,
                                result_code = response.body()!!.result_code,
                                status = response.body()!!.status,
                                username = response.body()!!.username,
                            )
//                                binding.user = response.body()
                            Toast.makeText(requireContext(), "Response: ${response.body().toString()}", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(requireContext(), "Try Again", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(requireContext(), "ERROR BODY: ${response.errorBody()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<UserData>, t: Throwable) {
                    Toast.makeText(requireContext(), "Fail:=>$t", Toast.LENGTH_SHORT).show()
                }
            })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}