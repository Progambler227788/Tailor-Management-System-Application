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
//        customersList = ArrayList()
//        customersList.add(RvCustomersData(1, "name", "phoneNumber"))
//        customersList.add(RvCustomersData(2, "name", "phoneNumber"))
//        customersList.add(RvCustomersData(3, "name", "phoneNumber"))
//        customersList.add(RvCustomersData(4, "name", "phoneNumber"))
//        customersList.add(RvCustomersData(5, "name", "phoneNumber"))
//        customerAdapter= RvAdapterCustomer(customersList,requireActivity())

        db = DBHelper(requireActivity(), null)

        binding.recyclerViewCustomers.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerViewCustomers.setHasFixedSize(true)
        //binding.recyclerViewCustomers.adapter = customerAdapter
        displayCustomers()

        binding.btnAddNewCustomer.setOnClickListener {
            val dialog = DialogNewCustDetails()
            dialog.show(requireActivity().supportFragmentManager, "Add New Customer")
        }

        notifyUser()
        return binding.root
    }

    fun displayCustomers() {
        var cursor = db.getAllCustomers()
        cursor.moveToFirst()
        customersList = ArrayList<RvCustomersData>()
        customersList.add(RvCustomersData(cursor.getInt(0), cursor.getString(1), cursor.getString(2)))
        while (cursor!!.moveToNext()) {
            customersList.add(RvCustomersData(cursor.getInt(0), cursor.getString(1), cursor.getString(2)))
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