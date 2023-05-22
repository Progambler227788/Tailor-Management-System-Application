package com.example.tailoringmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.tailoringmanagement.databinding.ActivityStartUpBinding

class StartUpActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityStartUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLoginUser.setOnClickListener {
            Toast.makeText(this, "Login Not Yet Implemented", Toast.LENGTH_SHORT).show()
        }
        binding.btnSignUpTailor.setOnClickListener {
            signUpUser("Tailor")
        }
        binding.btnSignUpCustomer.setOnClickListener {
            signUpUser("Customer")
        }
        binding.textViewRecoverAccount.setOnClickListener {
            Toast.makeText(this, "Recovery Not Yet Implemented", Toast.LENGTH_SHORT).show()
        }
        binding.textViewUseAsGuest.setOnClickListener {
            startActivity(Intent(this, HomeScreenActivity::class.java))
        }
    }

    private fun signUpUser(userType: String)
    {
        startActivity(Intent(this, SIgnUpActivity::class.java).putExtra("userType", userType))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
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