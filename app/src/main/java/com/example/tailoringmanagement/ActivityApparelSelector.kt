package com.example.tailoringmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.example.tailoringmanagement.databinding.ActivityApparelSelectorBinding

class ActivityApparelSelector : AppCompatActivity()
{
    private lateinit var binding: ActivityApparelSelectorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApparelSelectorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.setTitle("Manage Sizes")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val apparels = arrayOf("Shalwar", "Pants", "Trouser", "Qameez", "Full Sleeve Shirt",
            "Half Sleeve Shirt", "Coat", "Waist Coat")
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, apparels)
        binding.apparelListView.adapter = arrayAdapter
        val intent = Intent(this, ActivityEditCustomerSizes::class.java)
        binding.apparelListView.setOnItemClickListener { adapterView, view, position, id ->
            when (position) {
                0 -> {
                    intent.putExtra("apparel", "Shalwar")
                    startActivity(intent)
                } 1 -> {
                    intent.putExtra("apparel", "Pant")
                    startActivity(intent)
                } 2 -> {
                    intent.putExtra("apparel", "Trouser")
                    startActivity(intent)
                } 3 -> {
                    intent.putExtra("apparel", "Qameez")
                    startActivity(intent)
                } 4 -> {
                    intent.putExtra("apparel", "Full Sleeve Shirt")
                    startActivity(intent)
                } 5 -> {
                    intent.putExtra("apparel", "Half Sleeve Shirt")
                    startActivity(intent)
                } 6 -> {
                    intent.putExtra("apparel", "Coat")
                    startActivity(intent)
                } else -> {
                    intent.putExtra("apparel", "Waist Coat")
                    startActivity(intent)
                }
            }
        }

        
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            } else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}