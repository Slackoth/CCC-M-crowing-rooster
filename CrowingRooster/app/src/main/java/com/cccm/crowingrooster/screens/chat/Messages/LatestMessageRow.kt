package com.cccm.crowingrooster.screens.chat.Messages

import android.annotation.SuppressLint
import android.service.autofill.UserData
import android.util.Log
import android.widget.TextView
import com.bumptech.glide.Glide
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.UserDatabase
import com.firebase.ui.auth.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class LatestMessageRow(val chatMessage: ChatMessage): Item<GroupieViewHolder>() {
    var userpartner:UserDatabase?=null
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val chatotherCharUID:String
        if(chatMessage.from_uid== FirebaseAuth.getInstance().uid){
            chatotherCharUID=chatMessage.to_uid
        }else{
            chatotherCharUID=chatMessage.from_uid
        }
        Log.d("ChatF", chatotherCharUID)
        val ref = FirebaseDatabase.getInstance().getReference("/users/$chatotherCharUID")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            @SuppressLint("RestrictedApi")
            override fun onDataChange(p0: DataSnapshot) {
                 userpartner= p0.getValue(UserDatabase::class.java)
                viewHolder.itemView.findViewById<TextView>(R.id.chatname_text).text= userpartner?.username
                Glide.with(viewHolder.itemView).load(userpartner?.profileImageUrl).into(viewHolder.itemView.findViewById(
                    R.id.profile_img))

            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })

        viewHolder.itemView.findViewById<TextView>(R.id.chatlastmss_text).text = chatMessage.mssge
        //Glide.with(viewHolder.itemView).load(u)
        //getMssgeUser()
        //Glide.with(viewHolder.itemView).load()
    }

    override fun getLayout(): Int {
        return R.layout.chat_item_layout
    }
}