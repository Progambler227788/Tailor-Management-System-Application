package com.example.tailoringmanagement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tailoringmanagement.databinding.FragmentLoginTailorBinding

class LoginTailor : Fragment()
{
    private lateinit var binding: FragmentLoginTailorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginTailorBinding.inflate(inflater, container, false)
        binding.btnEnterShop.setOnClickListener {
            Toast.makeText(requireActivity(), "Not Yet Implemented", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }
}