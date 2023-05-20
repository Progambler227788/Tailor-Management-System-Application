package com.example.tailoringmanagement

import android.content.Intent
import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.tailoringmanagement.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.btnTailor.setOnClickListener {
            startActivity(Intent(this, Tailors::class.java))
        }
        binding.btnCustomer.setOnClickListener {
            startActivity(Intent(this,Customers::class.java))
        }
        binding.textViewAsGuest.setOnClickListener {
            val intent = Intent(this, HomeScreenActivity::class.java)
            startActivity(intent)
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
                Toast.makeText(this, "Setting Button Not Yet Implemented", Toast.LENGTH_SHORT)
                    .show()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}