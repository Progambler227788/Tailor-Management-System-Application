package com.example.tailoringmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
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

        launchFragment(FragmentHome())

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
            when (it.itemId) {
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
            setNegativeButton("No", null)
            setPositiveButton("Yes") {dialog, which->
                logout()
            }
        }
        val dialog = builder.create()
        dialog.show()
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
        supportFragmentManager.beginTransaction().replace(R.id.frameLayoutContainer, fragment).commit()
    }
}