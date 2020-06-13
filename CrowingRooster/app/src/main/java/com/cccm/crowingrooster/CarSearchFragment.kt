package com.cccm.crowingrooster

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cccm.crowingrooster.databinding.FragmentBatterySearchBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.DividerItemDecoration
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory

class CarSearchFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    var batteryList: MutableList<Any> = mutableListOf()

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_successful_orders, container, false)
        val bind = DataBindingUtil.inflate<FragmentBatterySearchBinding>(
            inflater, R.layout.fragment_battery_search,
            container, false
        )

        //Log.d(TAG, "onCreate: Started")
        //(activity as MainActivity).supportActionBar?.title = getString(R.string.successful_sales)

        recyclerView = bind.recyclerView
        batteryList.addAll(
            listOf(

            )
        )

        //Creating the RecyclerView Adapter
        val adapter = object : GenericRecyclerViewAdapter<Any>(batteryList, requireContext()) {
            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return ViewHolderFactory.bindView(view, viewType)
            }

            override fun getLayoutId(): Int {
                return R.layout.searchbt_item_layout
            }

            override fun getOnClickLayout(): () -> Unit {
                return {
                    this@CarSearchFragment.findNavController()
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

//        TODO: In case someday we need to change the orientation of the RecyclerView. DO NOT DELETE IT
//        recyclerView.layoutManager = LinearLayoutManager(requireContext(),
//            if (orientation == Configuration.ORIENTATION_LANDSCAPE) LinearLayoutManager.HORIZONTAL
//            else LinearLayoutManager.VERTICAL, false)

        return bind.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        //val inflater = menuInflater

        inflater.inflate(R.menu.search_menu, menu)

        val manager = (activity as MainActivity).getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView= searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo((activity as MainActivity).componentName))

        searchView.setOnQueryTextListener( object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {

                searchView.clearFocus()
                searchView.setQuery( "", false)
                val collapseActionView = searchItem.collapseActionView()

                Toast.makeText(requireContext(), "Looking for $query", Toast.LENGTH_LONG).show()

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false           }

        })



        super.onCreateOptionsMenu(menu, inflater)

//        return true

    }

}