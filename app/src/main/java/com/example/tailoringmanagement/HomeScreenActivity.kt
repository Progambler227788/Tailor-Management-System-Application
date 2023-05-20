package com.example.tailoringmanagement

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
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
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                }
                R.id.drawerItemProfile -> {
                    Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                }
                R.id.drawerItemMyShop -> {
                    Toast.makeText(this, "My Shop", Toast.LENGTH_SHORT).show()
                }
                R.id.drawerItemReportBug -> {
                    Toast.makeText(this, "Report a Bug", Toast.LENGTH_SHORT).show()
                }
                R.id.drawerItemLogout -> {
                    Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show()
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }
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