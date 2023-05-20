package com.example.tailoringmanagement

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.tailoringmanagement.databinding.ActivityHomeScreenBinding

class HomeScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.drawerToolBar.toolBar)
        binding.drawerToolBar.toolBar.setTitle(R.string.app_name)
        binding.drawerToolBar.toolBar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))

        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout,
            binding.drawerToolBar.toolBar, R.string.drawer_open, R.string.drawer_close
        )

        toggle.syncState()
        binding.drawerLayout.addDrawerListener(toggle)

        binding.drawerNavView.setNavigationItemSelectedListener {
            val id = it.itemId
            when (id) {
                R.id.drawerItemHome -> {
                    launchFragment(FragmentHome())
                }
                R.id.drawerItemProfile -> {
                    launchFragment(FragmentProfile())
                }
                R.id.drawerItemMyShop -> {
                    launchFragment(FragmentMyShop())
                }
                R.id.drawerItemReportBug -> {
                    launchFragment(FragmentReportBug())
                }
                R.id.drawerItemLogout -> {
                    logoutUserDialog()
                }
                else -> {
                    launchFragment(FragmentSettings())
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }
    }

    private fun logoutUserDialog() {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setMessage("Sure to logout?")
            setTitle("Logout")
            setNegativeButton("OK") {dialog, which->

            }
            setPositiveButton("OK") {dialog, which->
                logout()
            }
        }
    }

    private fun logout()
    {
        //Logic Behind Logging out will be written here
        Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START))
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        else
            @Suppress("DEPRECATION")
            super.onBackPressed()
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.frameLayoutContainer, fragment).commit()
    }
}