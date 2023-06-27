package com.example.tailoringmanagement.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.RecyclerView
import com.example.tailoringmanagement.activities.ChattingActivity
import com.example.tailoringmanagement.databinding.RvUserselectionBinding
import com.google.firebase.auth.FirebaseAuth

class RvAdapterChatting(private  var userList : ArrayList<UserChatModel>, var context : Context)
    : RecyclerView.Adapter<RvAdapterChatting.MyViewHolder> ()
{

    inner class MyViewHolder(var binding : RvUserselectionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =  RvUserselectionBinding.inflate(LayoutInflater.from(context), parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    @SuppressLint("SetTextI18n", "SuspiciousIndentation")
    override fun onBindViewHolder(holder: RvAdapterChatting.MyViewHolder, position: Int) {
        animation(holder.itemView)
        val user = userList[position]
        holder.binding.tvUserName.text =  user.name
        holder.itemView.setOnClickListener {
        val intent = Intent(context,ChattingActivity::class.java)
            intent.putExtra("name",user.name)
            intent.putExtra("id", user.key)
            context.startActivity(intent)
        }
    }


    private fun animation (view : View){
        val animation = AlphaAnimation(0.0f,1.0f)
        animation.duration = 1340
        view.startAnimation(animation)
    }

}