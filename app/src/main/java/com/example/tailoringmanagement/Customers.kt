package com.example.tailoringmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.example.tailoringmanagement.databinding.ActivityCustomersBinding

class Customers : AppCompatActivity() {
    private lateinit var binding: ActivityCustomersBinding
    private val login :  LoginCustomer = LoginCustomer()
    private val signUp : SignUpCustomer = SignUpCustomer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // default login
        replaceFragment(login)

        // actions on clicking
        binding.SignUpCust.setOnClickListener {

        if(checkStateOfFragment(signUp)) {
            Toast.makeText(this,"Signed Up",Toast.LENGTH_SHORT).show()
            Log.i("Customer", "Signed Up")
        }
            else  replaceFragment(signUp)
        }
        binding.LoginCust.setOnClickListener {
            if(checkStateOfFragment(login)) {
                Toast.makeText(this,"Logged in",Toast.LENGTH_SHORT).show()
                Log.i("Customer", "Logged In")
            }
            else  replaceFragment(login)
        }


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.app_bar_setting -> {
                Log.i("App Bar Log", "Setting Button Not Yet Implemented")
                Toast.makeText(this, "Setting Button Not Yet Implemented", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> {
                @Suppress("DEPRECATION")
                super.onBackPressed()
                return super.onOptionsItemSelected(item)
            }
        }
    }
    private fun checkStateOfFragment(fragment : Fragment) : Boolean {


        return (fragment.lifecycle.currentState == Lifecycle.State.RESUMED)
    }

    private fun replaceFragment (fragment : Fragment){
        supportFragmentManager.beginTransaction().replace(binding.fragmentFrame.id,fragment).commit()
    }

}