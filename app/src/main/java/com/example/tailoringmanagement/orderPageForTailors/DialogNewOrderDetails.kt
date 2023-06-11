package com.example.tailoringmanagement.orderPageForTailors

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.tailoringmanagement.R
import com.example.tailoringmanagement.localDB.OrderDBHelper
import java.util.*

class DialogNewOrderDetails : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())

        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.fragment_dialog_new_order_details, null)
        val btnCancel = dialogView.findViewById<Button>(R.id.btnCancelAddingOrder)
        val btnAdd = dialogView.findViewById<Button>(R.id.btnSaveNewOrder)
        val btnPickDate = dialogView.findViewById<Button>(R.id.btnPickDate)
        val tvDate = dialogView.findViewById<TextView>(R.id.tvDeliverDate)

        btnPickDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val currentYear = calendar.get(Calendar.YEAR)
            val currentMonth = calendar.get(Calendar.MONTH)
            val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    // Handle the selected date
                    val selectedDate = "$day/${month + 1}/$year"
                    // Do something with the selected date

                    // Set the selected date to a TextView or any other desired location
                    tvDate.text = selectedDate
                },
                currentYear,
                currentMonth,
                currentDay
            )

            // Show the DatePickerDialog
            datePickerDialog.show()
        }



        btnCancel.setOnClickListener {
            dismiss()
        }

        btnAdd.setOnClickListener {
            val tvOId = dialogView.findViewById<TextView>(R.id.inputOrderID)
            val tvOPrice = dialogView.findViewById<TextView>(R.id.inputOrderPrice)
            val tvOCid = dialogView.findViewById<TextView>(R.id.npCustomerID)
            val tvOEid = dialogView.findViewById<TextView>(R.id.npEmployeeID)


            val oid   =  tvOId.text?.toString() ?: ""
            val price =  tvOPrice.text?.toString() ?: ""
            val cid   =  tvOCid.text?.toString() ?: ""
            val eid   =  tvOEid.text?.toString() ?: ""
            val date = tvDate.text?.toString() ?: ""


            if(oid!="" && cid!="" && eid!="" && price!="" && date!="Pick Order Deliver Date") {
                val db = OrderDBHelper(requireContext(), null)
                if (db.addOrder(oid.toInt(),cid.toInt(),eid.toInt(),price.toFloat(),date))
                    Toast.makeText(requireContext(), "Order Added", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(requireContext(), "Order Already Exist", Toast.LENGTH_SHORT).show()
                requireActivity().supportFragmentManager.beginTransaction()

                    .replace(R.id.frameLayoutContainer, FragmentRVOrderRecord()).commit()
                dismiss()
            }
            else {
                Toast.makeText(requireActivity(), "Fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setView(dialogView).setMessage("Add New Order")
        return builder.create()
    }
}