package com.cccm.crowingrooster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cccm.crowingrooster.databinding.FragmentCanceledOrdersBinding

class CanceledOrdersFragment: Fragment() {

    lateinit var recyclerView: RecyclerView
    var orderList: MutableList<Any> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_successful_orders, container, false)
        val bind = DataBindingUtil.inflate<FragmentCanceledOrdersBinding>(
            inflater, R.layout.fragment_canceled_orders,
            container, false
        )

        //Log.d(TAG, "onCreate: Started")
        //(activity as MainActivity).supportActionBar?.title = getString(R.string.successful_sales)

        recyclerView = bind.recyclerView
        orderList.addAll(
            listOf(
                Order( num_order = 78, quantity = 300,
                    imgUrl = "https://i.musicaimg.com/letras/200/2482445.jpg",
                    date = "22/04/2020"
                ),

                Order( num_order = 71, quantity = 8,
                    imgUrl = "https://s.mxmcdn.net/images-storage/albums4/4/0/2/2/4/9/44942204_800_800.jpg",
                    date = "22/04/2020"
                ),

                Order( num_order = 70, quantity = 5,
                    imgUrl = "https://s.mxmcdn.net/images-storage/albums5/3/0/6/2/9/4/46492603_500_500.jpg",
                    date = "22/04/2020"
                ),

                Order( num_order = 69, quantity = 80,
                    imgUrl = "https://s.mxmcdn.net/images-storage/albums5/0/7/1/7/7/2/48277170_500_500.jpg",
                    date = "22/04/2020"
                ),

                Order( num_order = 78, quantity = 70,
                    imgUrl = "https://i.musicaimg.com/letras/200/2482445.jpg",
                    date = "22/04/2020"
                )

            )
        )

        //Creating the RecyclerView Adapter
        val adapter = object : GenericRecyclerViewAdapter<Any>(orderList, requireContext()) {
            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return ViewHolderFactory.bindView(view, viewType)
            }

            override fun getOnClickLayout(): () -> Unit {
                val dialog = OrderDetailsDialogFragment()
                return { -> dialog.show(requireActivity().supportFragmentManager, "OrderDetailsDialog") }
            }

            override fun getLayoutId(): Int {
                return R.layout.order_item_layout
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

//        TODO: In case someday we need to change the orientation of the RecyclerView. DO NOT DELETE IT
//        recyclerView.layoutManager = LinearLayoutManager(requireContext(),
//            if (orientation == Configuration.ORIENTATION_LANDSCAPE) LinearLayoutManager.HORIZONTAL
//            else LinearLayoutManager.VERTICAL, false)

        return bind.root
    }
}