package com.example.tailoringmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tailoringmanagement.databinding.ActivityCustomersBinding
import com.google.android.material.snackbar.Snackbar

class Customers : AppCompatActivity() {
    private lateinit var binding: ActivityCustomersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Toast.makeText(this,"Welcome Customer !! 😁",Toast.LENGTH_SHORT).show()
        Snackbar.make(binding.root,"Below buttons are used to login, create account and forgot password",Snackbar.LENGTH_SHORT).show()
        binding.SignUp.setOnClickListener {
            replaceFragment(SignUpCustomer())
        }
        binding.Login.setOnClickListener {
            replaceFragment(LoginCustomer())
        }


    }

    fun replaceFragment (fragment : Fragment){
        supportFragmentManager.beginTransaction().replace(binding.fragmentFrame.id,fragment).commit()
    }
}