package com.example.tailoringmanagement

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tailoringmanagement.databinding.ActivitySignUpBinding
import com.google.android.material.snackbar.Snackbar.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

@Suppress("UNREACHABLE_CODE", "DEPRECATION")
class SIgnUpActivity : AppCompatActivity(), FragmentInteractionListener
{
    private lateinit var binding: ActivitySignUpBinding
    private var userType: String? = null
    private lateinit var firebase: FirebaseAuth

    private lateinit var email : String
    private lateinit var password : String
    private lateinit var age : String
    private lateinit var phone : String
    private lateinit var name : String
    private lateinit var shopName : String
    private lateinit var confirmPass : String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebase = FirebaseAuth.getInstance()
        userType = intent.getStringExtra("userType")
        if (userType == "Tailor")
         replaceFragment(FragmentSignUpTailor())
       //   replaceFragment(FragmentPant())
        else
            replaceFragment(FragmentSignUpCustomer())

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.textViewSignPage.text = "Sign Up ${this.userType}"
        binding.btnSignUpUser.setOnClickListener {
            if (userType == "Tailor") {
                // In your Activity
                val fragment = supportFragmentManager.findFragmentById(R.id.frameLayoutSignUp) as? FragmentSignUpTailor
                fragment?.sendDataToActivity()
                if(validation()) {
                    signUp(email, password, userType)
                }
                else
                    make(findViewById(android.R.id.content), "Fill Fields Properly", LENGTH_SHORT).show()
                // fragment?.clearData()
            }

             else {
                Toast.makeText(this, "Customer Sign Up", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun replaceFragment(fragment: Fragment)
    {
        supportFragmentManager.beginTransaction().replace(R.id.frameLayoutSignUp, fragment).commit()
    }
//    override fun clearData(){
//        // implement any thing
//    }
    override fun sendDataToActivity(
        email: String,
        password: String,
        confirm: String,
        name: String,
        age: String,
        phone: String,
        shop: String
    ) {
       this.email = email
       this.password = password
       this.confirmPass = confirm
       this.name= name
       this.shopName = shop
       this.age = age
       this.phone = phone
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
    private fun validation() : Boolean {
        return ((name.isNotBlank() && email.isNotBlank() && password.isNotBlank() && confirmPass.isNotBlank()
            && age.isNotBlank() && shopName.isNotBlank() && phone.isNotBlank() )
                && (password==confirmPass))
    }
    private fun signUp(email: String, password: String, check : String?) {
        firebase.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Signup successful, you can retrieve the user from `task.result.user`

                    // Save user details to Realtime Database
                    var database : DatabaseReference? = null
                    if(check=="Tailor")
                        database = FirebaseDatabase.getInstance().getReference("Tailors")
                    else {
                        // to be implemented for customers
                    }

                    val key = database?.push()?.key!!

                    val details = TailorModel(name,age.toInt(),phone,shopName)

                    database.child(key).setValue(details)
                        .addOnSuccessListener {
                            // Tailor details saved successfully
                            Toast.makeText(
                                this,
                                " Successfully Signed Up",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        .addOnFailureListener {
                            // Failed to save tailor details
                            Toast.makeText(
                                this,
                                " Unsuccessful",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
                else {
                    // Signup failed
                    //  val exception = task.exception
                    Toast.makeText(this,"Error Occurred!!",Toast.LENGTH_SHORT).show()
                }
            }
    }
}