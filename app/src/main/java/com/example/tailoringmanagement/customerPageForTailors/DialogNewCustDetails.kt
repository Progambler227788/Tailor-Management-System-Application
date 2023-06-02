package com.example.tailoringmanagement.customerPageForTailors

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.tailoringmanagement.R
import com.example.tailoringmanagement.databinding.FragmentNewCustDetailsBinding

class DialogNewCustDetails : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.fragment_new_cust_details, null)
        val btnCancel = dialogView.findViewById<Button>(R.id.btnCancelAddingCustomer)
        val btnAdd = dialogView.findViewById<Button>(R.id.btnSaveNewCustomer)
        btnCancel.setOnClickListener {
            dismiss()
        }
        btnAdd.setOnClickListener {
            val tvId = dialogView.findViewById<TextView>(R.id.inputNewCustomerID)
            val tvName = dialogView.findViewById<TextView>(R.id.inputNewCustomerName)
            val tvPhone = dialogView.findViewById<TextView>(R.id.inputNewCustomerPhoneNumber)
            val id = tvId.text?.toString() ?: ""
            val name = tvName.text?.toString() ?: ""
            val number = tvPhone.text?.toString() ?: ""
            if(id!="" && name!="" && number!="") {
                Toast.makeText(requireActivity(), "Login Not Yet Implemented", Toast.LENGTH_SHORT).show()
                dismiss()
            }
            else {
                Toast.makeText(requireActivity(), "Fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setView(dialogView).setMessage("Add New Customer")
        return builder.create()
    }
}