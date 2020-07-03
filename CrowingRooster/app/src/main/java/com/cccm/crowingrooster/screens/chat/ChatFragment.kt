package com.cccm.crowingrooster.screens.chat

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.service.autofill.UserData
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cccm.crowingrooster.MainActivity
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.databinding.FragmentChatBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.Chat
import com.cccm.crowingrooster.generic_recyclerview_adapter.DividerItemDecoration
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.UserDatabase
import com.cccm.crowingrooster.screens.chat.Messages.ChatMessage
import com.cccm.crowingrooster.screens.chat.Messages.LatestMessageRow
import com.cccm.crowingrooster.screens.sales.successful_sales.TAG
import com.firebase.ui.auth.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_main.*


/**
 * A simple [Fragment] subclass.
 */
class ChatFragment : Fragment() {



    private lateinit var recyclerView: RecyclerView
    private var chatList: MutableList<Any> = mutableListOf()
    val adapter = GroupAdapter<GroupieViewHolder>()
    companion object{
         var currentUser:UserDatabase?=null
         var clickedUser:UserDatabase?=null
    }
    val latestMessagesMap = HashMap<String, ChatMessage>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val bind = DataBindingUtil.inflate<FragmentChatBinding>(
            inflater, R.layout.fragment_chat,
            container, false
        )
        fetchCurrentUser()
        val Useruid:String?= FirebaseAuth.getInstance().uid
        //val user= Intent().getParcelableArrayExtra()
        (activity as MainActivity).run {
            showTopBar()
            supportActionBar?.title = getString(R.string.chat).capitalize()
            navigation_view.menu.clear()
            navigation_view.inflateMenu(R.menu.seller_drawer_menu_navigation)
        }


        listenforlatestMessages()


        recyclerView = bind.recyclerViewChat




        //FireBaseAuth return to login
        val uid = FirebaseAuth.getInstance().uid
        if (uid==null){
            view?.findNavController()?.navigate(R.id.logInFragment)
        }



        /*//Creating the RecyclerView Adapter
        val adapter = object : GenericRecyclerViewAdapter<Any>(chatList, requireContext()) {

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {

                return ViewHolderFactory.bindView(view, viewType)
            }

            override fun getLayoutId(): Int {
                return R.layout.chat_item_layout
            }

            override fun getOnClickLayout(): () -> Unit {
                return {
                    this@ChatFragment.findNavController()
                        .navigate(R.id.action_chatFragment_to_chatLogFragment)                }
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //Adding the divider
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                R.drawable.recyclerview_divider
            )
        )
        recyclerView.adapter = adapter*/

        val recycleview= bind.recyclerViewChat
        val manager = LinearLayoutManager(context)
        recyclerView.layoutManager = manager
        recyclerView.setHasFixedSize(true)

        recycleview.adapter= adapter
        //recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), )


        adapter.setOnItemClickListener { item, view ->
            Log.d(TAG, "123")
            view.findNavController().navigate(R.id.action_chatFragment_to_chatLogFragment)

            val row = item as LatestMessageRow
            clickedUser=row.userpartner

        }



        return bind.root
    }


    private fun fetchCurrentUser() {
        val uid = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        ref.addListenerForSingleValueEvent(object: ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {
                currentUser = p0.getValue(UserDatabase::class.java)
                Log.d("LatestMessages", "Current user ${currentUser?.profileImageUrl}")

            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }

    private fun listenforlatestMessages() {
        val fromid = FirebaseAuth.getInstance().uid
        val ref= FirebaseDatabase.getInstance().getReference ("/latest_messages/$fromid")
            ref.addChildEventListener(object: ChildEventListener{

                override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                    val ChatMessage= p0.getValue(ChatMessage::class.java) ?:return
                    latestMessagesMap[p0.key!!]= ChatMessage
                    refreshRecyclerView()
                }

                override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                    val chatMessage = p0.getValue(ChatMessage::class.java) ?: return
                    latestMessagesMap[p0.key!!]= chatMessage
                    refreshRecyclerView()

                }
                override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                }
                override fun onChildRemoved(p0: DataSnapshot) {
                }
                override fun onCancelled(p0: DatabaseError) {
                }

            })
    }

    private fun refreshRecyclerView(){
        adapter.clear()
        latestMessagesMap.values.forEach {
            adapter.add(LatestMessageRow(it))
        }
    }
    private fun getMssgeUser(){

    }




}
