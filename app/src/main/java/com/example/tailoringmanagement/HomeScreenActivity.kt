package com.example.tailoringmanagement
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.tailoringmanagement.databinding.ActivityHomeScreenBinding
import com.example.tailoringmanagement.customerPageForTailors.FragmentRVCustomerRecord

class HomeScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.drawerToolBar.toolBar)
        //binding.drawerToolBar.toolBar.setTitle(R.string.app_name)
        binding.drawerToolBar.toolBar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))

        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout,
            binding.drawerToolBar.toolBar, R.string.drawer_open, R.string.drawer_close
        )

        toggle.syncState()
        binding.drawerLayout.addDrawerListener(toggle)

        binding.drawerNavView.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.drawerItemCustomers -> {
                    launchFragment("Customers", FragmentRVCustomerRecord())
                    Toast.makeText(this, "Customers", Toast.LENGTH_SHORT).show()
                }
                R.id.drawerItemOrders -> {
                    Toast.makeText(this, "Orders", Toast.LENGTH_SHORT).show()
                }
                R.id.drawerItemEmployees -> {
                    Toast.makeText(this, "Employees", Toast.LENGTH_SHORT).show()
                }
                R.id.drawerItemProfile -> {
                    launchFragment("Profile", FragmentProfile())
                    Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                }
                R.id.drawerItemMyShop -> {
                    launchFragment("My Shop", FragmentMyShop())
                    Toast.makeText(this, "My Shop", Toast.LENGTH_SHORT).show()
                }
                R.id.drawerItemReportBug -> {
                    launchFragment("Report a Bug", FragmentReportBug())
                    Toast.makeText(this, "Report a Bug", Toast.LENGTH_SHORT).show()
                }
                R.id.drawerItemLogout -> {
                    Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    launchFragment("Settings", FragmentSettings())
                    Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show()
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }
    }

    override fun onStart() {
        super.onStart()
        launchFragment("Customers", FragmentRVCustomerRecord())

    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START))
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        else
            @Suppress("DEPRECATION")
            super.onBackPressed()
    }

    private fun launchFragment(toolBarTitle: String, fragment: Fragment) {
        binding.drawerToolBar.toolBar.setTitle(toolBarTitle)
        supportFragmentManager.beginTransaction().replace(R.id.frameLayoutContainer, fragment).commit()
    }
}