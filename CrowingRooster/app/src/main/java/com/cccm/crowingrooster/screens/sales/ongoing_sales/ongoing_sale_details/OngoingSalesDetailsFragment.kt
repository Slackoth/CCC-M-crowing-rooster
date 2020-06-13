package com.cccm.crowingrooster.screens.sales.ongoing_sales.ongoing_sale_details

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cccm.crowingrooster.*
import com.cccm.crowingrooster.databinding.FragmentOngoingSalesDetailsBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.SaleDetails
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory
import com.cccm.crowingrooster.screens.sales.ongoing_sales.confirm_sale.ConfirmSaleFragment
import com.google.android.material.textfield.TextInputEditText

/**
 * A simple [Fragment] subclass.
 */
class OngoingSalesDetailsFragment : Fragment() {

    private lateinit var bind: FragmentOngoingSalesDetailsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var clientEditT: TextInputEditText
    private lateinit var emailEditT: TextInputEditText
    private lateinit var dateEditT: TextInputEditText
    private var ongoingSaleList: MutableList<Any> = mutableListOf()
    private lateinit var confirmButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_ongoing_sales_details, container, false)
        bind = DataBindingUtil.inflate(inflater,
            R.layout.fragment_ongoing_sales_details, container, false)

        (activity as MainActivity).supportActionBar?.title = getString(
            R.string.details
        )

        bind.apply {
            confirmButton = confirmBtt
            recyclerView = recyclerViewOsd
            clientEditT = clientEt
            emailEditT = emailEt
            dateEditT = dateEt
        }

        clientEditT.inputType = InputType.TYPE_NULL
        emailEditT.inputType = InputType.TYPE_NULL
        dateEditT.inputType = InputType.TYPE_NULL

        ongoingSaleList.addAll(
            listOf(
                SaleDetails(
                    10,
                    "20F-Derecha-Azul"
                ),
                SaleDetails(
                    15,
                    "21D-Izquierda-Amarilla"
                ),
                SaleDetails(
                    2,
                    "22E-Derecha-Amarilla"
                ),
                SaleDetails(
                    30,
                    "23Q-Izquierda-Azul"
                ),
                SaleDetails(
                    6,
                    "24P-Derecha-Azul"
                )
            )
        )


        val adapter = object : GenericRecyclerViewAdapter<Any>(ongoingSaleList,requireContext()) {
            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return ViewHolderFactory.bindView(
                    view,
                    viewType
                )
            }

            override fun getLayoutId(): Int {
                return R.layout.sale_details_item_layout
            }

            override fun getOnClickLayout(): () -> Unit {
                return {}
            }

        }

        confirmButton.setOnClickListener {
            val dialog =
                ConfirmSaleFragment()
            dialog.show(requireActivity().supportFragmentManager,"ConfirmDialog")
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //recyclerView.addItemDecoration(DividerItemDecoration(requireContext(),R.drawable.recyclerview_divider))
        recyclerView.adapter = adapter

        return bind.root
    }

}
