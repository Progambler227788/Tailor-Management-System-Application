package com.example.tailoringmanagement.customerPageForTailors

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
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

        binding.recyclerViewCustomers.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewCustomers.setHasFixedSize(true)

        //val itemDecoration = ItemSpacingDecoration(10, 10, 20, 20)
        //binding.recyclerViewCustomers.addItemDecoration(itemDecoration)

       // displayCustomers()

        binding.btnAddNewCustomer.setOnClickListener {
            val dialog = DialogNewCustDetails()
            dialog.show(requireActivity().supportFragmentManager, "Add New Customer")
            displayCustomers()
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        displayCustomers()
    }

    private fun displayCustomers() {
        val cursor = db.getAllCustomers()
        customersList = ArrayList()
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

}