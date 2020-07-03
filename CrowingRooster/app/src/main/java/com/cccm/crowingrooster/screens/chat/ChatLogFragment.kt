package com.cccm.crowingrooster.screens.chat

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.databinding.FragmentChatLogBinding
import com.cccm.crowingrooster.screens.chat.Messages.ChatMessage
import com.firebase.ui.auth.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item


class ChatLogFragment : Fragment() {

    private var MssgeList: MutableList<Any> = mutableListOf()
    private lateinit var recyclerView: RecyclerView
    val adapter = GroupAdapter<GroupieViewHolder>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind= DataBindingUtil.inflate<FragmentChatLogBinding>(inflater,
            R.layout.fragment_chat_log,
            container,false)
        //sendBTN
        bind.sendButtonChatLog.setOnClickListener {
            Log.d("ChatLogFragment",bind.edittextChatLog.text.toString())

            actionSendMessage(bind.edittextChatLog.text.toString())
        }

        //setContentView<FragmentChatLogBinding>(Activity(),R.layout.fragment_chat_log) // add this line
        recyclerView =bind.recyclerViewChatLog
        val manager = LinearLayoutManager(context)
        recyclerView.layoutManager = manager
        recyclerView.setHasFixedSize(true)

        listenAllMessages()
        adapter.notifyDataSetChanged()

        recyclerView.adapter=adapter

        return bind.root
    }

    private fun actionSendMessage(mensaje:String){
        //val reference= FirebaseDatabase.getInstance().getReference("/messages").push()
        val fromId= FirebaseAuth.getInstance().uid.toString()
        val reference= FirebaseDatabase.getInstance().getReference("/user_messages/$fromId/W0PqXizI6oPuOY2wzxUPd41nOcU2").push()
        val chatMessage= ChatMessage(reference.key!!, mensaje, fromId,"W0PqXizI6oPuOY2wzxUPd41nOcU2", System.currentTimeMillis()/1000)

        reference.setValue(chatMessage)
            .addOnSuccessListener {
                Log.d("ChatLogFragment","Mensaje Subido perros!!!!!!!")

            }

    }

    private fun listenAllMessages(){
        val ref = FirebaseDatabase.getInstance().getReference("messages")
            ref.addChildEventListener(object: ChildEventListener{
                override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                    //TODO("Not yet implemented")
                    val chatMessage= p0.getValue(ChatMessage::class.java)
                    if (chatMessage != null) {
                        if (chatMessage.from_uid==FirebaseAuth.getInstance().uid){

                            ///NO URGENTE, NO MEE DETECTA LA LLAMADA DE FIREBASE AUTH USER ID, HACERLO POR FB REALTIME

                            val phtouser:String= ChatFragment.currentUser?.profileImageUrl.toString()
                            adapter.add(ChatToItem(chatMessage.mssge,phtouser )   )
                        }
                        //debo setear un else if para el usuario que recibe
                        else{
                            adapter.add(ChatFromItem(chatMessage.mssge)   )
                        }



                    }



                }

                override fun onChildRemoved(p0: DataSnapshot) {
                    TODO("Not yet implemented")
                }

                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                    TODO("Not yet implemented")
                }

                override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                    TODO("Not yet implemented")
                }
            })
    }

}

//funciones chat

class ChatFromItem(var text: String): Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val MssgefromText:TextView=viewHolder.itemView.findViewById(R.id.textview_from_row)

        MssgefromText.text=text
    }

    override fun getLayout(): Int {
        return R.layout.chat_from_item_layout
    }
}

class ChatToItem(val text: String, val photo:String): Item<GroupieViewHolder>() {

    @SuppressLint("RestrictedApi")
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val MssgetoText:TextView=viewHolder.itemView.findViewById(R.id.textview_to_row)
        Glide.with(viewHolder.itemView).load(photo).into(viewHolder.itemView.findViewById(R.id.imageview_chat_to_row))
        Log.d("Chatlog",photo)

        MssgetoText.text=text

    }

    override fun getLayout(): Int {
        return R.layout.chat_to_item_layout
    }
}
