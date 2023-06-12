package com.example.tailoringmanagement

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity

class RestartActivity {
    fun restartAct(context : Context) {
        val intent = Intent(context, HomeScreenActivity :: class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        intent.putExtra("restartFragmentOrder", true)
        context.startActivity(intent)
    }

}