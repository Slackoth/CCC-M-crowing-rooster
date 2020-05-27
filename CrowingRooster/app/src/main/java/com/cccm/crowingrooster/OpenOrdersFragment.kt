package com.cccm.crowingrooster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cccm.crowingrooster.databinding.FragmentOpenOrdersBinding

class OpenOrdersFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private var OpenOrdersList: MutableList<Any> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = DataBindingUtil.inflate<FragmentOpenOrdersBinding>(
            inflater,
            R.layout.fragment_open_orders,
            container,
            false
        )

        recyclerView = bind.recyclerViewOo
        OpenOrdersList.addAll(
            listOf(
                OpenOrder(
                    "20/05/2020", "Vancouver",
                    "https://pbs.twimg.com/media/ECWkaynXsAItbrK?format=jpg&name=medium"
                ),
                OpenOrder(
                    "21/05/2020", "Netherlands",
                    "https://pbs.twimg.com/media/ECWkaynXsAItbrK?format=jpg&name=medium"
                ),
                OpenOrder(
                    "22/05/2020", "Korea la mala",
                    "https://pbs.twimg.com/media/ECWkaynXsAItbrK?format=jpg&name=medium"
                ),
                OpenOrder(
                    "23/05/2020", "Brazil",
                    "https://pbs.twimg.com/media/ECWkaynXsAItbrK?format=jpg&name=medium"
                )
            )
        )

        //Creating the RecyclerView Adapter
        val adapter = object : GenericRecyclerViewAdapter<Any>(OpenOrdersList, requireContext()) {

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {

                return ViewHolderFactory.bindView(view, viewType)
            }

            override fun getLayoutId(): Int {

                return R.layout.open_orders_item_layout
            }

            override fun getOnClickLayout(): () -> Unit {

                return { ->
                    this@OpenOrdersFragment.findNavController()
                        .navigate(R.id.action_openOrdersFragment_to_openOrdersDetailsFragment)
                }
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