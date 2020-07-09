package com.cccm.crowingrooster

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cccm.crowingrooster.databinding.FragmentConcludedOrdersBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.DividerItemDecoration
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.OpenOrder
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory

class ConcludedOrdersFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private var concludedOrdersList: MutableList<Any> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = DataBindingUtil.inflate<FragmentConcludedOrdersBinding>(
            inflater,
            R.layout.fragment_concluded_orders,
            container,
            false
        )

        setHasOptionsMenu(true)

        recyclerView = bind.recyclerViewCo
        concludedOrdersList.addAll(
            listOf(
                OpenOrder(
                    "20/05/2020", "Vancouver",
                    "https://pbs.twimg.com/media/ECWkaynXsAItbrK?format=jpg&name=medium"
                ),
                OpenOrder(
                    "20/05/2020", "Vancouver",
                    "https://pbs.twimg.com/media/ECWkaynXsAItbrK?format=jpg&name=medium"
                ),
                OpenOrder(
                    "20/05/2020", "Vancouver",
                    "https://pbs.twimg.com/media/ECWkaynXsAItbrK?format=jpg&name=medium"
                ),
                OpenOrder(
                    "20/05/2020", "Vancouver",
                    "https://pbs.twimg.com/media/ECWkaynXsAItbrK?format=jpg&name=medium"
                )
            )
        )

        val adapter =
            object : GenericRecyclerViewAdapter<Any>(concludedOrdersList, requireContext()) {
                override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                    return ViewHolderFactory.bindView(view, viewType)
                }

                override fun getLayoutId(): Int {
                    return R.layout.open_orders_item_layout
                }

                override fun getOnClickLayout(): (List<Any>) -> Unit {
                    val dialog = ConcludedOrderDetailsDialogFragment()
                    return { it ->
                        dialog.show(
                            requireActivity().supportFragmentManager,
                            "ConcludeOrderDetails"
                        )
                    }
                }
            }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

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