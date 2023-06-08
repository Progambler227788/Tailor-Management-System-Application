package com.example.tailoringmanagement

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.tailoringmanagement.databinding.ActivityEditCustomerInfoBinding
import com.example.tailoringmanagement.localDB.DBHelper

class EditCustomerInfo : AppCompatActivity() {

    private lateinit var binding: ActivityEditCustomerInfoBinding
    private var id: String? = null
    private var name: String? = null
    private var ph: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditCustomerInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.title = "Edit Customer"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        id = intent.getStringExtra("id")
        name = intent.getStringExtra("name")
        ph = intent.getStringExtra("ph")

        binding.inputCustomerID.setText(id)
        binding.inputCustomerID.isEnabled = false
        binding.inputCustomerID.setOnClickListener {
            Toast.makeText(this, "ID Can Not Be Changed!", Toast.LENGTH_SHORT).show()
        }
        binding.inputCustomerName.setText(name)
        binding.inputCustomerPhoneNumber.setText(ph)

        binding.btnCancelEditingCustomer.setOnClickListener {
            if (checkIfThereIsChange())
            {
                showCancelAlert()
            }else
                onBackPressedDispatcher.onBackPressed()
        }

        binding.btnSaveCustomer.setOnClickListener {
            if (checkIfThereIsChange())
                onBackPressedDispatcher.onBackPressed()
            else {
                val db = DBHelper(this, null)
                db.updateCustomerInfo(id!!.toInt(), DBHelper.COLUMN_PHONE, ""+binding.inputCustomerPhoneNumber.text)
                db.updateCustomerInfo(id!!.toInt(), DBHelper.COLUMN_NAME, ""+binding.inputCustomerName.text)

//                if (name != ""+binding.inputCustomerName.text)
//                {
//                    db.updateCustomerInfo(id!!.toInt(), DBHelper.COLUMN_NAME, ""+binding.inputCustomerName.text)
//                }
//                if (ph != ""+binding.inputCustomerPhoneNumber.text)
//                {
//                    db.updateCustomerInfo(id!!.toInt(), DBHelper.COLUMN_PHONE, ""+binding.inputCustomerPhoneNumber.text)
//                }
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                if (checkIfThereIsChange())
                {
                    showCancelAlert()
                }else
                    onBackPressedDispatcher.onBackPressed()
                return true
            } else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    private fun checkIfThereIsChange(): Boolean {
        return name != ""+binding.inputCustomerName.text &&
                ph != ""+binding.inputCustomerPhoneNumber.text
    }

    private fun showCancelAlert(){
        AlertDialog.Builder(this).setTitle("Alert")
            .setMessage("Sure To Cancel?\nAll Changes Will Be Lost.")
            .setNegativeButton("No", null)
            .setPositiveButton("Yes") { _, _ ->
                onBackPressedDispatcher.onBackPressed()
            }
            .create()
            .show()
    }

    private fun updateCustomerData()
    {

    }
}