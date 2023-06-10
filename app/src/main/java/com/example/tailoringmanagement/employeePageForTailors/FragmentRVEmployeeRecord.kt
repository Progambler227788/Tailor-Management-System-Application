package com.example.tailoringmanagement.employeePageForTailors

import android.os.Bundle
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
import com.example.tailoringmanagement.localDB.EmpDBHelper

class FragmentRVEmployeeRecord : Fragment()
{
    private lateinit var binding: FragmentRVEmployeeRecordBinding
    private lateinit var db: EmpDBHelper
    private lateinit var employeeAdapter: RVAdapterEmployee
    private lateinit var empList: ArrayList<RVEmployeeData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRVEmployeeRecordBinding.inflate(layoutInflater, container, false)

        db = EmpDBHelper(requireActivity(), null)
        binding.recyclerViewEmployee.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewEmployee.setHasFixedSize(true)

        displayEmployee()

        binding.btnAddNewEmp.setOnClickListener {
            val dialog = DialogNewEmpDetails()
            dialog.show(requireActivity().supportFragmentManager, "Add New Customer")
            //displayEmployee()
        }

        return binding.root
    }

    fun displayEmployee() {
        val cursor = db.getAllEmployees()
        empList = ArrayList()
        while (cursor!!.moveToNext()) {
            empList.add(
                RVEmployeeData(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2).toInt(),
                    cursor.getString(3)
                )
            )
        }
        employeeAdapter = RVAdapterEmployee(empList, requireActivity())
        binding.recyclerViewEmployee.adapter = employeeAdapter
    }

}