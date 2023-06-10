package com.example.tailoringmanagement.employeePageForTailors

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tailoringmanagement.R
import com.example.tailoringmanagement.databinding.RvEmployeeBinding
import com.example.tailoringmanagement.localDB.EmpDBHelper

class RVAdapterEmployee(private  var employeeList : ArrayList<RVEmployeeData>, var context : Context)
    : RecyclerView.Adapter<RVAdapterEmployee.MyViewHolder> ()
{
    inner class MyViewHolder(var binding : RvEmployeeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RvEmployeeBinding.inflate(LayoutInflater.from(context), parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    override fun onBindViewHolder(holder: RVAdapterEmployee.MyViewHolder, position: Int) {
        animation(holder.itemView)
        holder.binding.tvEmpID.text = context.getString(R.string.emp_id, employeeList[position].id)
        holder.binding.tvEmpName.text = employeeList[position].name
        holder.binding.tvEmpNumSuits.text = context.getString(R.string.emp_num_suits, employeeList[position].numSuit)
        holder.binding.tvEmpPhone.text = context.getString(R.string.emp_phone, employeeList[position].phone)

        holder.binding.btnDeleteEmp.setOnClickListener {
            val removedPosition = holder.bindingAdapterPosition

            AlertDialog.Builder(context)
                .setMessage("Sure to delete customer?\n All sizes will be deleted.")
                .setTitle("Delete Customer")
                .setPositiveButton("Yes") { _, _ ->
                    Toast.makeText(context, "Customer Deleted!", Toast.LENGTH_SHORT).show()
                    val customer = employeeList[removedPosition]
                    employeeList.removeAt(removedPosition)
                    notifyItemRemoved(removedPosition)
                    val db = EmpDBHelper(context, null)
                    db.deleteEmployee(customer.id)
                }
                .setNegativeButton("No", null)
                .create()
                .show()
        }

        holder.binding.btnEditEmpDetails.setOnClickListener {
            val updatePos = holder.bindingAdapterPosition
            val emp = employeeList[updatePos]
            val intent = Intent(context, EditEmployeeDetails::class.java)
            intent.putExtra("id", emp.id)
            intent.putExtra("name", emp.name)
            intent.putExtra("nsuits", emp.numSuit)
            intent.putExtra("phone", emp.phone)
            context.startActivity(intent)
        }

        holder.binding.btnEmpPayment.setOnClickListener {

        }

        holder.binding.btnIncrementSuit.setOnClickListener {
            val updatePos = holder.bindingAdapterPosition
            employeeList[updatePos].numSuit = (employeeList[updatePos].numSuit + 1)
            notifyItemChanged(updatePos)
            val db = EmpDBHelper(context, null)
            db.updateEmployeeInfo(employeeList[updatePos].id, EmpDBHelper.COLUMN_NO_SUITS, employeeList[updatePos].numSuit.toString())
        }

    }

    private fun animation (view : View){
        val animation = AlphaAnimation(0.0f,1.0f)
        animation.duration = 1340
        view.startAnimation(animation)
    }
}