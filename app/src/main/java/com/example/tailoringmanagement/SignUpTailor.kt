package com.example.tailoringmanagement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
import com.example.tailoringmanagement.databinding.FragmentSignUpTailorBinding

class SignUpTailor : Fragment()
{
    private lateinit var binding: FragmentSignUpTailorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        binding = FragmentSignUpTailorBinding.inflate(layoutInflater)
        return binding.root
    }

}