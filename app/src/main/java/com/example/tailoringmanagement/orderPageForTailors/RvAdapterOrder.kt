package com.example.tailoringmanagement.orderPageForTailors

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.core.content.ContextCompat
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

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RvAdapterOrder.MyViewHolder, position: Int) {
        animation(holder.itemView)
        holder.binding.tvOrderID.text =
            context.getString(R.string.order_id, orderList[position].oid)
        holder.binding.tvEmployeeId.text =
            context.getString(R.string.emp_id, orderList[position].eid)
        holder.binding.tvCustomerIdOrder.text =
            context.getString(R.string.customer_id, orderList[position].cid)
        holder.binding.tvPayment.text =
            context.getString(R.string.payment_order, orderList[position].payment)
        holder.binding.tvDeliverDate.text = "Order Date: " + orderList[position].date


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
            val updatePos = holder.bindingAdapterPosition
            val order = orderList[updatePos]
            val intent = Intent(context, EditOrderDetails::class.java)
            intent.putExtra("oid", order.oid.toString())
            intent.putExtra("cid", order.cid.toString())
            intent.putExtra("eid", order.eid.toString())
            intent.putExtra("payment", order.payment.toString())
            intent.putExtra("date", order.date)
            context.startActivity(intent)
        }


        val currentItem = orderList[position]

        holder.binding.btnMarkOrderComplete.setOnClickListener {
            // Case when user clicks it
            // Change the color of the clicked item
            val clickedItemColor = ContextCompat.getColor(context, R.color.light_green)
            saveClickedItemColor(currentItem.oid.toString(), clickedItemColor)

            // Update the item view
            holder.itemView.setBackgroundColor(clickedItemColor)
        }
        // Case when recycler view become visible

        // Retrieve the saved color for the clicked item
        val clickedItemColor = getClickedItemColor(currentItem.oid.toString())
        holder.itemView.setBackgroundColor(clickedItemColor)

    }
    private fun saveClickedItemColor(orderId: String, colorResId: Int) {
        val sharedPreferences = context.getSharedPreferences("ClickedItems", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(orderId, colorResId)
        editor.apply()
    }

    private fun getClickedItemColor(orderId: String): Int {
        val sharedPreferences = context.getSharedPreferences("ClickedItems", Context.MODE_PRIVATE)
        return sharedPreferences.getInt(orderId, android.R.color.transparent)
    }


    private fun animation (view : View){
        val animation = AlphaAnimation(0.0f,1.0f)
        animation.duration = 1340
        view.startAnimation(animation)
    }
    fun updateOrders(newOrders: ArrayList<RVOrderData>) {
        orderList.clear()
        orderList.addAll(newOrders)
        notifyDataSetChanged()
        Log.i("Called","updating")
    }
}