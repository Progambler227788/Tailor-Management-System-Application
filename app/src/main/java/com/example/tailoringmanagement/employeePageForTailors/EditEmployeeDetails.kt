package com.example.tailoringmanagement.employeePageForTailors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tailoringmanagement.databinding.ActivityEditEmployeeDetailsBinding

class EditEmployeeDetails : AppCompatActivity() {

    private lateinit var binding: ActivityEditEmployeeDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditEmployeeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}