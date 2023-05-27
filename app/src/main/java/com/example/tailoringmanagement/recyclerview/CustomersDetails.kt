package com.example.tailoringmanagement.recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tailoringmanagement.databinding.ActivityCustomersDetailsBinding

class CustomersDetails : AppCompatActivity() {
    private lateinit var binding : ActivityCustomersDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomersDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSave.setOnClickListener {
            val id = binding.inputCustomerID.text?.toString() ?: ""
            val name = binding.inputCustomerName.text?.toString() ?: ""
            val number = binding.inputCustomerPhoneNumber.text?.toString() ?: ""
            if(id!="" && name!="" && number!="") {
                val intent = Intent(this, CustomerRv::class.java)
                intent.putExtra("id", id)
                intent.putExtra("name", name)
                intent.putExtra("phone-number", number)
                startActivity(intent)
                finish()
            }
            else {
                Toast.makeText(this,"Fill all fields",Toast.LENGTH_SHORT).show()
            }
        }

    }
}