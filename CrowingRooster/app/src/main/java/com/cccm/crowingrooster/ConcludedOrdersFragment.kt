package com.cccm.crowingrooster

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cccm.crowingrooster.databinding.FragmentConcludedOrdersBinding
import kotlinx.android.synthetic.main.activity_main.*

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

                override fun getOnClickLayout(): () -> Unit {
                    val dialog = ConcludedOrderDetailsDialogFragment()
                    return { ->
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.concluded_orders_more_options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_order_time_co, R.id.action_order_date_co -> {
                val dialog = AscDescDialogFragment()
                dialog.show(requireActivity().supportFragmentManager, "AscDescDialog")
                true
            }
            R.id.action_out_co -> {
                findNavController().navigate(R.id.logInFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}