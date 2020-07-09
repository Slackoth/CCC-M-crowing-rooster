package com.cccm.crowingrooster

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cccm.crowingrooster.databinding.FragmentCanceledOrdersBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.Canceled_Order
import com.cccm.crowingrooster.generic_recyclerview_adapter.DividerItemDecoration
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory
import com.cccm.crowingrooster.screens.ascending_descending_search.AscDescDialogFragment

class CanceledOrdersFragment: Fragment() {

    lateinit var recyclerView: RecyclerView
    var canceled_orderList: MutableList<Any> = mutableListOf()

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

        setHasOptionsMenu(true)

        //Log.d(TAG, "onCreate: Started")
        //(activity as MainActivity).supportActionBar?.title = getString(R.string.successful_sales)

        recyclerView = bind.recyclerView
        canceled_orderList.addAll(
            listOf(
                Canceled_Order(
                    quantity = 300,
                    imgUrl = "https://i.musicaimg.com/letras/200/2482445.jpg",
                    date = "22/04/2020"
                ),
                Canceled_Order(
                    quantity = 300,
                    imgUrl = "https://s.mxmcdn.net/images-storage/albums4/4/0/2/2/4/9/44942204_800_800.jpg",
                    date = "22/04/2020"
                )

            )
        )

        //Creating the RecyclerView Adapter
        val adapter = object : GenericRecyclerViewAdapter<Any>(canceled_orderList, requireContext()) {
            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return ViewHolderFactory.bindView(view, viewType)
            }

            override fun getOnClickLayout(): (List<Any>) -> Unit {
                val dialog = CanceledOrderDetailsDialogFragment()
                return { it -> dialog.show(requireActivity().supportFragmentManager, "CanceledOrderDetailsDialog") }
            }

            override fun getLayoutId(): Int {
                return R.layout.canceled_order_item_layout
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.canceled_order_more_options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_order_date_cao -> {
                val dialog =
                    AscDescDialogFragment()
                dialog.show(requireActivity().supportFragmentManager, "AscDescDialog")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}