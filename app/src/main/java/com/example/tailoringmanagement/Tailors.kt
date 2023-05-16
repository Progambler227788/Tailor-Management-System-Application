package com.example.tailoringmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tailoringmanagement.databinding.ActivityTailorsBinding


class Tailors : AppCompatActivity()
{
    private lateinit var binding: ActivityTailorsBinding
    private var signUpTailor: SignUpTailor? = null
    private var loginTailor: LoginTailor? = null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityTailorsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        replaceFragment(LoginTailor())

        binding.LoginTailor.setOnClickListener {
            if (loginTailor == null)
                loginTailor = LoginTailor()
            if (replaceFragment(loginTailor!!)) {
                Log.i("Test", "Log In")
            }
        }

        binding.SignupTailor.setOnClickListener {
            if (signUpTailor == null)
                signUpTailor = SignUpTailor()
            if (replaceFragment(signUpTailor!!)) {
                Log.i("Test", "Sign Up")
            }
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

    private fun replaceFragment (fragment : Fragment): Boolean
    {
        if (fragment.isVisible)
            return true
        supportFragmentManager.beginTransaction().replace(binding.tailorFragmentFrame.id,fragment).commit()
        return false
    }
}