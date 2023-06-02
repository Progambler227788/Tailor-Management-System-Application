package com.example.tailoringmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.tailoringmanagement.databinding.ActivityStartUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class StartUpActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityStartUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSignUp.setOnClickListener {
            signUpUser()
        }
        binding.btnLoginUser.setOnClickListener {
              loginUser()
        }
        binding.textViewRecoverAccount.setOnClickListener {
            Toast.makeText(this, "Recovery Not Yet Implemented", Toast.LENGTH_SHORT).show()
        }
        binding.textViewUseAsGuest.setOnClickListener {
            startActivity(Intent(this, HomeScreenActivity::class.java))
        }
    }

    private fun signUpUser()
    {
        startActivity(Intent(this, SIgnUpActivity::class.java))
    }
    private fun loginUser(){
        val email = binding.loginUser.text.toString()
        val password = binding.loginPassword.text.toString()
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener {
            task ->
            if(task.isSuccessful){
             val database = FirebaseDatabase.getInstance().reference.child("Users")
             val key = task.result.user!!.uid
             database.child(key).addListenerForSingleValueEvent(object : ValueEventListener{
                 // functions to retrieve data
             override fun onDataChange(snapshot: DataSnapshot) {
                       val userType = snapshot.child("userType").value.toString()

                        if(userType=="Tailor"){
                            Toast.makeText(this@StartUpActivity,"Tailor Logged In",Toast.LENGTH_SHORT).show()
                          startActivity(Intent(this@StartUpActivity,HomeScreenActivity::class.java))
                        }
                        else {
                            Toast.makeText(this@StartUpActivity,"Customer Logged In",Toast.LENGTH_SHORT).show()
                        }
                    }

             override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(this@StartUpActivity,"Got error :$error",Toast.LENGTH_SHORT).show()
                    }
                })
            }
            else {
                Toast.makeText(this@StartUpActivity,"Login denied. Invalid Credentials",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.app_bar_setting -> {
                Toast.makeText(this, "Settings Not Yet Implemented", Toast.LENGTH_SHORT).show()
                return true
            } else -> {
                super.onBackPressed()
                return true
            }
        }
    }
}