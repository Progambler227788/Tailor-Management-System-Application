package com.example.tailoringmanagement.recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tailoringmanagement.databinding.ActivityCustomerRvBinding

class CustomerRv : AppCompatActivity() {
    private lateinit var binding : ActivityCustomerRvBinding
    private lateinit var customerAdapter : RvAdapterCustomer
    private lateinit var customersList : ArrayList<RvCustomersData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerRvBinding.inflate(layoutInflater)
        setContentView(binding.root)
        customersList = ArrayList()
        customersList.add(RvCustomersData(1, "name", "phoneNumber"))
        customersList.add(RvCustomersData(2, "name", "phoneNumber"))
        customersList.add(RvCustomersData(3, "name", "phoneNumber"))
        customersList.add(RvCustomersData(4, "name", "phoneNumber"))
        customersList.add(RvCustomersData(5, "name", "phoneNumber"))
        customerAdapter= RvAdapterCustomer(customersList,this)
        binding.recyclerViewCustomers.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCustomers.adapter = customerAdapter

     //   customerAdapter.notifyItemInserted(customersList.size - 1)
        binding.btnAddCustomer.setOnClickListener {
            startActivity(Intent(this,CustomersDetails::class.java))

        }
       notifyUser()



    }
    override fun onResume() {
        super.onResume()
        Log.i("Resume","Called")

    }

    private fun notifyUser() {
    //    Toast.makeText(this,"Inside",Toast.LENGTH_SHORT).show()
        val id = intent?.getStringExtra("id") ?: ""
        val name = intent?.getStringExtra("name") ?: ""
        val phoneNumber = intent?.getStringExtra("phone-number") ?: ""
        if (id != "" && name != "" && phoneNumber != "") {
         //    Toast.makeText(this,"Inside",Toast.LENGTH_SHORT).show()
                customersList.add(RvCustomersData(id.toInt(), name, phoneNumber))
                customerAdapter.notifyItemInserted(customersList.size - 1)
            }
    }
}