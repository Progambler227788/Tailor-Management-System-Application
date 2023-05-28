package com.example.tailoringmanagement.recyclerview

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tailoringmanagement.R
import com.example.tailoringmanagement.databinding.FragmentRVCustomerRecordBinding

class FragmentRVCustomerRecord : Fragment() {
    private lateinit var binding: FragmentRVCustomerRecordBinding
    private lateinit var customerAdapter : RvAdapterCustomer
    private lateinit var customersList : ArrayList<RvCustomersData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRVCustomerRecordBinding.inflate(layoutInflater, container, false)
        customersList = ArrayList()
        customersList.add(RvCustomersData(1, "name", "phoneNumber"))
        customersList.add(RvCustomersData(2, "name", "phoneNumber"))
        customersList.add(RvCustomersData(3, "name", "phoneNumber"))
        customersList.add(RvCustomersData(4, "name", "phoneNumber"))
        customersList.add(RvCustomersData(5, "name", "phoneNumber"))
        customerAdapter= RvAdapterCustomer(customersList,requireActivity())

        binding.recyclerViewCustomers.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerViewCustomers.adapter = customerAdapter

        binding.btnAddNewCustomer.setOnClickListener {
            startActivity(Intent(requireActivity(), CustomersDetails::class.java))

        }

        notifyUser()
        return binding.root
    }

    private fun notifyUser() {
        //    Toast.makeText(this,"Inside",Toast.LENGTH_SHORT).show()
//        val id = intent?.getStringExtra("id") ?: ""
//        val name = intent?.getStringExtra("name") ?: ""
//        val phoneNumber = intent?.getStringExtra("phone-number") ?: ""
//        if (id != "" && name != "" && phoneNumber != "") {
//            //    Toast.makeText(this,"Inside",Toast.LENGTH_SHORT).show()
//            customersList.add(RvCustomersData(id.toInt(), name, phoneNumber))
//            customerAdapter.notifyItemInserted(customersList.size - 1)
//        }
    }
}