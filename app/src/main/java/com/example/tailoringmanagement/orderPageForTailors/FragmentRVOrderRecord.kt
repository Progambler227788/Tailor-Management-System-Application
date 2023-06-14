package com.example.tailoringmanagement.orderPageForTailors

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tailoringmanagement.databinding.FragmentRVOrderRecordBinding
import com.example.tailoringmanagement.localDB.OrderDBHelper

class FragmentRVOrderRecord : Fragment()
{
    private lateinit var binding: FragmentRVOrderRecordBinding
    private lateinit var db: OrderDBHelper
    private lateinit var orderAdapter: RvAdapterOrder
    private lateinit var orderList: ArrayList<RVOrderData>

    override fun onResume() {
        super.onResume()
        displayOrders()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
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

    private fun displayOrders() {
        val cursor = db.getAllOrders()
        orderList = ArrayList()
        while (cursor!!.moveToNext()) {
            orderList.add(
                RVOrderData(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getFloat(3),
                    cursor.getString(4),
                )
            )
        }
        orderAdapter = RvAdapterOrder(orderList, requireActivity())
        binding.recyclerViewOrder.adapter = orderAdapter
    }

}