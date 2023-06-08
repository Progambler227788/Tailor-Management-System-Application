package com.example.tailoringmanagement

import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.tailoringmanagement.databinding.FragmentNewCustDetailsBinding

class EditCustomerDetails: AppCompatActivity()
{
    private lateinit var binding: FragmentNewCustDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?)
    {
        super.onCreate(savedInstanceState, persistentState)
        binding = FragmentNewCustDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.title = "Edit Customer"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.inputNewCustomerID.setText(intent.getStringExtra("id"))
        binding.inputNewCustomerName.setText(intent.getStringExtra("name"))
        binding.inputNewCustomerPhoneNumber.setText(intent.getStringExtra("ph"))

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                @Suppress("DEPRECATION")
                onBackPressed()
                return true
            } else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}