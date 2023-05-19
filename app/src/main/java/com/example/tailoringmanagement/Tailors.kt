package com.example.tailoringmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.example.tailoringmanagement.databinding.ActivityTailorsBinding


class Tailors : AppCompatActivity()
{
    private lateinit var binding: ActivityTailorsBinding
    private val loginTailor :  LoginTailor = LoginTailor()
    private val signUpTailor : SignUpTailor = SignUpTailor()
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityTailorsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        replaceFragment(loginTailor)

        binding.LoginTailor.setOnClickListener {
            if(checkStateOfFragment(loginTailor)) {
                Toast.makeText(this,"Logged in",Toast.LENGTH_SHORT).show()
                Log.i("Tailor", "Logged In")
            }
            else  replaceFragment(loginTailor)
        }

        binding.SignupTailor.setOnClickListener {
            if(checkStateOfFragment(signUpTailor)) {
                Toast.makeText(this,"Signed Up",Toast.LENGTH_SHORT).show()
                Log.i("Tailor", "Signed Up")
            }
            else  replaceFragment(signUpTailor)
        }
        
        binding.textViewRecoverAccount.setOnClickListener {
            Toast.makeText(this, "Not Yet Implemented", Toast.LENGTH_SHORT).show()
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


        return fragment.lifecycle.currentState == Lifecycle.State.RESUMED
    }

    private fun replaceFragment (fragment : Fragment)
    {
        supportFragmentManager.beginTransaction().replace(binding.tailorFragmentFrame.id,fragment).commit()
    }
}