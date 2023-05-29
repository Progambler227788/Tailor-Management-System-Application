package com.example.tailoringmanagement.customerPageForTailors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tailoringmanagement.databinding.RvCustomersBinding

class RvAdapterCustomer(private  var customerList : ArrayList<RvCustomersData>, var context : Context) : RecyclerView.Adapter<RvAdapterCustomer.MyViewHolder> () {
    private var removedPosition : Int ? = null
    inner class MyViewHolder(var binding : RvCustomersBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RvCustomersBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return customerList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        animation(holder.itemView)
        holder.binding.tvCustomerId.text = customerList[position].id.toString()
        holder.binding.tvCustomerName.text = customerList[position].name
        holder.binding.tvCustomerPhoneNumber.text = customerList[position].phoneNumber
        holder.binding.btnDelete.setOnClickListener {
            val removedPosition = holder.bindingAdapterPosition
            customerList.removeAt(removedPosition)
            notifyItemRemoved(removedPosition)
        }
        holder.binding.btnEditCustomers.setOnClickListener{
            Toast.makeText(context,"To be implemented",Toast.LENGTH_SHORT).show()
        }
        holder.binding.btnEdit.setOnClickListener{
            Toast.makeText(context,"To be implemented",Toast.LENGTH_SHORT).show()
        }

    }

    private fun animation (view : View){
        val animation = AlphaAnimation(0.0f,1.0f)
        animation.duration = 1340
        view.startAnimation(animation)
    }
    private fun getRemoveItemPosition() : Int? {
        return removedPosition
    }
}