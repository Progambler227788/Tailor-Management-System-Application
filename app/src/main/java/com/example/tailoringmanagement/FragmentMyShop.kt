package com.example.tailoringmanagement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tailoringmanagement.databinding.FragmentMyShopBinding

class FragmentMyShop : Fragment()
{
    private lateinit var binding: FragmentMyShopBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyShopBinding.inflate(layoutInflater)
        return binding.root
    }

}