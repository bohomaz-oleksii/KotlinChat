package com.thorproject.chat.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.thorproject.chat.messages.NewMessageActivity.Companion.USER_KEY
import com.thorproject.chat.models.User

class NotificationService() : Service() {



    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val user = intent?.getParcelableExtra<User>(USER_KEY)
        Log.d("Service", user?.username)

        val ref = FirebaseDatabase.getInstance().getReference("/user-messages/${user?.uid}")
        ref.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("Service", snapshot.children.toString())
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}