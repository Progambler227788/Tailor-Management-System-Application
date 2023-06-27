package com.example.tailoringmanagement.activities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tailoringmanagement.databinding.ActivityChattingBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class ChattingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChattingBinding
    private lateinit var messageList : ArrayList<Message>
    private lateinit var adapter : RvAdapterMessaging
    private var senderArea : String? = null
    private var receiverArea : String? = null
    private lateinit var db : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChattingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseDatabase.getInstance().reference

        val senderId = FirebaseAuth.getInstance().currentUser?.uid


        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val name = intent.getStringExtra("name")
        val id = intent.getStringExtra("id")

        binding.messageBox.text.clear()

        supportActionBar!!.title = name
        messageList = ArrayList()
        senderArea = id + senderId
        receiverArea = id + senderId

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RvAdapterMessaging(messageList,this)
        binding.recyclerView.adapter = adapter

        db.child("Chats").child(senderArea!!).child("messages").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                messageList.clear()
                for (message in snapshot.children){
                    val text = message.child("message").value.toString()
                    val senderId = message.child("senderId").value.toString()
                    messageList.add(Message(text,senderId))
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })




        binding.sendButton.setOnClickListener {
            val message = binding.messageBox.text.toString()
            val messageObj = Message(message,senderId)
            db.child("Chats").child(senderArea!!).child("messages").push().
                    setValue(messageObj).addOnSuccessListener {

            }
            binding.messageBox.text.clear()

        }


    }
    
}