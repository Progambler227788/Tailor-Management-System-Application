package com.example.tailoringmanagement.customerPageForTailors

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.tailoringmanagement.HomeScreenActivity
import com.example.tailoringmanagement.R
import com.example.tailoringmanagement.localDB.DBHelper


class DialogNewCustDetails : DialogFragment() {
   // private var listener: OnCustomerAddedInterface? = null
//    verride fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnCustomerAddedInterface) {
//            listener = context
//        }
//    } o


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
                val db = DBHelper(requireContext(), null)
                db.addCustomer(id.toInt(), name, number)
                Toast.makeText(requireContext(), "Customer Added", Toast.LENGTH_SHORT).show()
             //   requireActivity().restartActivity()
                restartActivity()
             //   listener?.onCustomerAdded()

                dismiss()
            }
            else {
                Toast.makeText(requireActivity(), "Fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setView(dialogView).setMessage("Add New Customer")
        return builder.create()
    }
   fun restartActivity() {
        val intent = Intent(requireActivity(), HomeScreenActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        requireActivity().startActivity(intent)
        requireActivity().finish()
    }

//    fun onCustomerAdded() {
//        val fragmentRVCustomerRecord = targetFragment as? FragmentRVCustomerRecord
//        fragmentRVCustomerRecord?.restartFragment()
//    }

}