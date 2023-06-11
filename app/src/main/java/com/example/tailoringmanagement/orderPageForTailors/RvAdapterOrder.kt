package com.example.tailoringmanagement.orderPageForTailors

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
import com.example.tailoringmanagement.databinding.RvOrdersBinding
import com.example.tailoringmanagement.localDB.OrderDBHelper

class RvAdapterOrder(private  var orderList : ArrayList<RVOrderData>, var context : Context)
    : RecyclerView.Adapter<RvAdapterOrder.MyViewHolder> ()
{
    inner class MyViewHolder(var binding : RvOrdersBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RvOrdersBinding.inflate(LayoutInflater.from(context), parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: RvAdapterOrder.MyViewHolder, position: Int) {
        animation(holder.itemView)
        holder.binding.tvOrderID.text = context.getString(R.string.order_id, orderList[position].oid)
        holder.binding.tvEmployeeId.text = context.getString(R.string.emp_id, orderList[position].eid)
        holder.binding.tvCustomerIdOrder.text = context.getString(R.string.customer_id, orderList[position].cid)
        holder.binding.tvPayment.text = context.getString(R.string.payment_order, orderList[position].payment)
        holder.binding.tvDeliverDate.text =  orderList[position].date


        holder.binding.btnDeleteOrder.setOnClickListener {
            val removedPosition = holder.bindingAdapterPosition

            AlertDialog.Builder(context)
                .setMessage("Sure to Delete Order?")
                .setTitle("Delete Order")
                .setPositiveButton("Yes") { _, _ ->
                    Toast.makeText(context, "Order Deleted!", Toast.LENGTH_SHORT).show()
                    val order = orderList[removedPosition]
                    orderList.removeAt(removedPosition)
                    notifyItemRemoved(removedPosition)
                    val db = OrderDBHelper(context, null)
                    db.deleteOrder(order.oid)
                }
                .setNegativeButton("No", null)
                .create()
                .show()
        }

        holder.binding.btnEditOrderDetails.setOnClickListener {
//            val updatePos = holder.bindingAdapterPosition
//            val emp = employeeList[updatePos]
//            val intent = Intent(context, EditEmployeeDetails::class.java)
//            intent.putExtra("id", emp.id.toString())
//            intent.putExtra("name", emp.name)
//            intent.putExtra("phone", emp.phone)
//            context.startActivity(intent)
        }

        holder.binding.btnMarkOrderComplete.setOnClickListener {

        }


    }

    private fun animation (view : View){
        val animation = AlphaAnimation(0.0f,1.0f)
        animation.duration = 1340
        view.startAnimation(animation)
    }
}