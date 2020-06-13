package com.cccm.crowingrooster

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cccm.crowingrooster.databinding.FragmentOngoingOrdersDetailsBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.OrderDetails
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory
import com.google.android.material.textfield.TextInputEditText

class OngoingOrdersDetailsFragment : Fragment() {



    private lateinit var bind: FragmentOngoingOrdersDetailsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var clientEditT: TextInputEditText
    private lateinit var emailEditT: TextInputEditText
    private lateinit var dateEditT: TextInputEditText
    private var ongoingOrderList: MutableList<Any> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_ongoing_sales_details, container, false)
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_ongoing_orders_details, container, false)

        (activity as MainActivity).supportActionBar?.title = getString(R.string.details)

        bind.apply {

            recyclerView = recyclerViewOsd
            clientEditT = sellerEt
            emailEditT = emailEt
            dateEditT = dateEt
        }

        clientEditT.inputType = InputType.TYPE_NULL
        emailEditT.inputType = InputType.TYPE_NULL
        dateEditT.inputType = InputType.TYPE_NULL

        ongoingOrderList.addAll(
            listOf(
                OrderDetails(
                    10,
                    "20F-Derecha-Azul"
                ),
                OrderDetails(
                    15,
                    "21D-Izquierda-Amarilla"
                ),
                OrderDetails(
                    2,
                    "22E-Derecha-Amarilla"
                ),
                OrderDetails(
                    30,
                    "23Q-Izquierda-Azul"
                ),
                OrderDetails(
                    6,
                    "24P-Derecha-Azul"
                )
            )
        )


        val adapter = object : GenericRecyclerViewAdapter<Any>(ongoingOrderList,requireContext()) {
            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return ViewHolderFactory.bindView(view,viewType)
            }

            override fun getLayoutId(): Int {
                return R.layout.order_details_item
            }

            override fun getOnClickLayout(): () -> Unit {
                return {}
            }

        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //recyclerView.addItemDecoration(DividerItemDecoration(requireContext(),R.drawable.recyclerview_divider))
        recyclerView.adapter = adapter

        return bind.root
    }
}