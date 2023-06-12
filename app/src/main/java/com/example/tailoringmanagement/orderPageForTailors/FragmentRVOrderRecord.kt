package com.example.tailoringmanagement.orderPageForTailors

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tailoringmanagement.R
import com.example.tailoringmanagement.customerPageForTailors.DialogNewCustDetails
import com.example.tailoringmanagement.customerPageForTailors.RvAdapterCustomer
import com.example.tailoringmanagement.customerPageForTailors.RvCustomersData
import com.example.tailoringmanagement.databinding.FragmentRVEmployeeRecordBinding
import com.example.tailoringmanagement.databinding.FragmentRVOrderRecordBinding
import com.example.tailoringmanagement.localDB.EmpDBHelper
import com.example.tailoringmanagement.localDB.OrderDBHelper

class FragmentRVOrderRecord : Fragment()
{
    private lateinit var binding: FragmentRVOrderRecordBinding
    private lateinit var db: OrderDBHelper
    private lateinit var orderAdpapter: RvAdapterOrder
    private lateinit var empList: ArrayList<RVOrderData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        Log.i("Fragment","Called")
        binding = FragmentRVOrderRecordBinding.inflate(layoutInflater, container, false)

        db = OrderDBHelper(requireActivity(), null)
        binding.recyclerViewOrder.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewOrder.setHasFixedSize(true)

        displayOrders()

        binding.btnAddNewOrder.setOnClickListener {
            val dialog = DialogNewOrderDetails()
            dialog.show(requireActivity().supportFragmentManager, "Add New Order")
        }

        return binding.root
    }

    fun displayOrders() {
        val cursor = db.getAllOrders()
        empList = ArrayList()
        while (cursor!!.moveToNext()) {
            empList.add(
                RVOrderData(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getFloat(3),
                    cursor.getString(4),
                )
            )
        }
        orderAdpapter = RvAdapterOrder(empList, requireActivity())
        binding.recyclerViewOrder.adapter = orderAdpapter
    }

}