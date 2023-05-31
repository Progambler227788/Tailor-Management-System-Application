package com.example.tailoringmanagement

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.tailoringmanagement.databinding.FragmentSignUpTailorBinding

interface FragmentInteractionListener {
    fun sendDataToActivity(
        email: String,
        password: String,
        confirm: String,
        name: String,
        age: String,
        phone: String,
        shop: String
    )
  //  fun clearData()
}

class FragmentSignUpTailor : Fragment() {
    private lateinit var binding: FragmentSignUpTailorBinding
    private var listener: FragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        binding = FragmentSignUpTailorBinding.inflate(layoutInflater)

        return binding.root
    }


    // In TailorFragment.kt

    // In your Fragment
//    fun clearData () {
//        listener?.clearData()
//        binding.editTextTailorName.text.clear()
//        binding.editTextAge.text.clear()
//        binding.editTextTailorEmailAddress.text.clear()
//        binding.editTextTailorPassword.text.clear()
//        binding.editTextConfirmTailorPassword.text.clear()
//        binding.editTextTailorShopName.text.clear()
//    }
    fun sendDataToActivity() {
        listener?.sendDataToActivity(
            binding.editTextTailorEmailAddress.text.toString(),
            binding.editTextTailorPassword.text.toString(),
            binding.editTextConfirmTailorPassword.text.toString(),
            binding.editTextTailorName.text.toString(),
            binding.editTextAge.text.toString(),
            binding.editTextTailorPhone.text.toString(),
            binding.editTextTailorShopName.text.toString()
        )
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Check if the activity implements the FragmentInteractionListener interface
        if (context is FragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement FragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }




}