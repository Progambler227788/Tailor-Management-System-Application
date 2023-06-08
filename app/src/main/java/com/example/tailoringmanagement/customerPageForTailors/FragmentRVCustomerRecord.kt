package com.example.tailoringmanagement.customerPageForTailors

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tailoringmanagement.R
import com.example.tailoringmanagement.databinding.FragmentRVCustomerRecordBinding
import com.example.tailoringmanagement.localDB.DBHelper

class FragmentRVCustomerRecord : Fragment() {
    private lateinit var binding: FragmentRVCustomerRecordBinding
    private lateinit var db: DBHelper
    private lateinit var customerAdapter : RvAdapterCustomer
    private lateinit var customersList : ArrayList<RvCustomersData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRVCustomerRecordBinding.inflate(layoutInflater, container, false)

        db = DBHelper(requireActivity(), null)

        binding.recyclerViewCustomers.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerViewCustomers.setHasFixedSize(true)
        displayCustomers()

        binding.btnAddNewCustomer.setOnClickListener {
            val dialog = DialogNewCustDetails()
            dialog.show(requireActivity().supportFragmentManager, "Add New Customer")
            displayCustomers()
        }
        notifyUser()
        return binding.root
    }

    private fun displayCustomers() {
        val cursor = db.getAllCustomers()
        customersList = ArrayList<RvCustomersData>()
        while (cursor!!.moveToNext()) {
            customersList.add(
                RvCustomersData(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                )
            )
        }
        customerAdapter = RvAdapterCustomer(customersList, requireActivity())
        binding.recyclerViewCustomers.adapter = customerAdapter
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