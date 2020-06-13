package com.cccm.crowingrooster.screens.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cccm.crowingrooster.MainActivity
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.databinding.FragmentChatBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.Chat
import com.cccm.crowingrooster.generic_recyclerview_adapter.DividerItemDecoration
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory
import kotlinx.android.synthetic.main.activity_main.*


/**
 * A simple [Fragment] subclass.
 */
class ChatFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private var chatList: MutableList<Any> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val bind = DataBindingUtil.inflate<FragmentChatBinding>(
            inflater, R.layout.fragment_chat,
            container, false
        )

        (activity as MainActivity).run {
            showTopBar()
            supportActionBar?.title = getString(R.string.chat).capitalize()
            navigation_view.menu.clear()
            navigation_view.inflateMenu(R.menu.seller_drawer_menu_navigation)
        }


        recyclerView = bind.recyclerViewChat
        chatList.addAll(
            listOf(
                Chat(
                    "Doña Pipo", "Spicy jalapeno bacon ipsum dolor amet corne", 1,
                    "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB"
                ),
                Chat(
                    "Don kleexon", "Chavales ando electrico", 2,
                    "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB"
                ),
                Chat(
                    "Don Sisas", "Valorant de 850V para mi pc", 5,
                    "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB"
                ),
                Chat(
                    "Mr. Luisillo pillín", "Se sabe el nombre de las baterias con un rayo?", 2,
                    "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB"
                )


            )
        )

        //Creating the RecyclerView Adapter
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
                        .navigate(R.id.productFragment)                }
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
        recyclerView.adapter = adapter

        return bind.root
    }

}
