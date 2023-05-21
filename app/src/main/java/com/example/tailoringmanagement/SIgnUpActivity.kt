package com.example.tailoringmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tailoringmanagement.databinding.ActivitySignUpBinding

class SIgnUpActivity : AppCompatActivity()
{
    private lateinit var binding: ActivitySignUpBinding
    private var userType: String? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userType = intent.getStringExtra("userType")
        if (userType == "Tailor")
            replaceFragment(FragmentSignUpTailor())
        else
            replaceFragment(FragmentSignUpCustomer())

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.textViewSignPage.text = "Sign Up " + userType
        binding.btnSignUpUser.setOnClickListener {
            if (userType == "Tailor") {
                Toast.makeText(this, "Tailor Sign Up", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Customer Sign Up", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun replaceFragment(fragment: Fragment)
    {
        supportFragmentManager.beginTransaction().replace(R.id.frameLayoutSignUp, fragment).commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.app_bar_setting -> {
                Toast.makeText(this, "Settings Not Yet Implemented", Toast.LENGTH_SHORT).show()
                return true
            } else -> {
                super.onBackPressed()
                return true
            }
        }
    }
}