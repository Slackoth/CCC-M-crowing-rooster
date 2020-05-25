package com.cccm.crowingrooster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cccm.crowingrooster.databinding.FragmentOngoingOrdersBinding


class OngoingOrdersFragment : Fragment () {


    lateinit var recyclerView: RecyclerView
    var orderList: MutableList<Any> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_successful_orders, container, false)
        val bind = DataBindingUtil.inflate<FragmentOngoingOrdersBinding>(

            inflater, R.layout.fragment_ongoing_orders,
            container, false

        )

        //Log.d(TAG, "onCreate: Started")
        //(activity as MainActivity).supportActionBar?.title = getString(R.string.successful_sales)

        recyclerView = bind.recyclerView
        orderList.addAll(
            listOf(
                Order( num_order = 69, quantity = 100,
                    imgUrl = "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB",
                    date = "22/04/2020"
                ),

                Order( num_order = 70, quantity = 200,
                    imgUrl = "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB",
                    date = "22/04/2020"
                ),

                Order( num_order = 71, quantity = 300,
                    imgUrl = "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB",
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
                val dialog = SaleDetailsDialogFragment()
                return { -> dialog.show(requireActivity().supportFragmentManager, "SaleDetailsDialog") }
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