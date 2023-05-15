package com.example.tailoringmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.tailoringmanagement.databinding.ActivityCustomersBinding
import com.google.android.material.snackbar.Snackbar

class Customers : AppCompatActivity() {
    private lateinit var binding: ActivityCustomersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Snackbar.make(binding.root,"Welcome Customer. Scroll to see other fields!! 😎",Snackbar.LENGTH_LONG).show()
        binding.SignUp.setOnClickListener {
            replaceFragment(SignUpCustomer())
        }
        binding.Login.setOnClickListener {
            replaceFragment(LoginCustomer())
        }


    }

    private fun replaceFragment (fragment : Fragment){
        supportFragmentManager.beginTransaction().replace(binding.fragmentFrame.id,fragment).commit()
    }
}