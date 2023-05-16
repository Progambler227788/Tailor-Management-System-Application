package com.example.tailoringmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tailoringmanagement.databinding.ActivityTailorsBinding
import com.google.android.material.snackbar.Snackbar

class Tailors : AppCompatActivity()
{
    private lateinit var binding: ActivityTailorsBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityTailorsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        replaceFragment(LoginTailor())
        binding.LoginTailor.setOnClickListener {
            replaceFragment(LoginTailor())
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

    private fun replaceFragment (fragment : Fragment){
        supportFragmentManager.beginTransaction().replace(binding.tailorFragmentFrame.id,fragment).commit()
    }
}