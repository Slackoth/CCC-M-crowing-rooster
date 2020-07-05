package com.cccm.crowingrooster.screens.sales.successful_sales

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cccm.crowingrooster.*
import com.cccm.crowingrooster.databinding.FragmentSuccessfulSalesBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.DividerItemDecoration
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory
import com.cccm.crowingrooster.screens.sales.successful_sales.successful_sale_details.SaleDetailsDialogFragment

/**
 * A simple [Fragment] subclass.
 */

const val TAG = "HighOrder"

class SuccessfulSalesFragment : Fragment() {
    private lateinit var bind: FragmentSuccessfulSalesBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: SuccessfulSalesViewModel


    @SuppressLint("LogNotTimber")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(
            inflater, R.layout.fragment_successful_sales,
            container, false
        )

        viewModel = ViewModelProvider(this).get(SuccessfulSalesViewModel::class.java)

        bind.apply {
            recyclerView = succesfulSalesRv
            lifecycleOwner = this@SuccessfulSalesFragment
        }

        initRecyclerview()

        viewModel.successfulSalesViewModel.observe(viewLifecycleOwner, Observer {
            recyclerView.adapter?.notifyDataSetChanged()
        })

//        TODO: In case someday we need to change the orientation of the RecyclerView. DO NOT DELETE IT
//        recyclerView.layoutManager = LinearLayoutManager(requireContext(),
//            if (orientation == Configuration.ORIENTATION_LANDSCAPE) LinearLayoutManager.HORIZONTAL
//            else LinearLayoutManager.VERTICAL, false)

        return bind.root
    }

    private fun initRecyclerview() {
        //Creating the RecyclerView Adapter
         val adapter = object : GenericRecyclerViewAdapter<Any>(viewModel.successfulSalesViewModel.value!!, requireContext()) {
            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return ViewHolderFactory.bindView(
                    view,
                    viewType
                )
            }

            override fun getOnClickLayout(): () -> Unit {
                val dialog =
                    SaleDetailsDialogFragment()
                return { -> dialog.show(requireActivity().supportFragmentManager, "SaleDetailsDialog") }
            }

            override fun getLayoutId(): Int {
                return R.layout.sale_item_layout
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
    }
}
