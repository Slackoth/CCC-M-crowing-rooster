package com.cccm.crowingrooster.screens.catalogue

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.databinding.FragmentCatalogueBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.DividerItemDecoration
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.Product
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory

/**
 * A simple [Fragment] subclass.
 */

private lateinit var linearLayoutManager: LinearLayoutManager

class CatalogueFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private var productlist: MutableList<Any> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = DataBindingUtil.inflate<FragmentCatalogueBinding>(
            inflater, R.layout.fragment_catalogue,
            container, false
        )



        recyclerView = bind.recyclerViewCatalogue
        productlist.addAll(
            listOf(
                Product(
                    "Baterias rasho",
                    "Spicy jalapeno bacon ipsum dolor amet corned beef leberkas ribeye biltong capicola chicken shoulder meatloaf pork belly. ",
                    "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB"
                ),
                Product(
                    "Baterias Trueno",
                    "huck swine pancetta kevin, beef pork loin pork chop short ribs chislic pork.huck swine pancetta kevin, beef pork loin pork chop short ribs chislic pork.",
                    "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB"
                ),
                Product(
                    "Baterias Rayito",
                    "Brisket spare ribs alcatra short loin jowl venison pork loin beef ribs pastrami drumstick chuck",
                    "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB"
                ),
                Product(
                    "Baterias Esclavas del Blanco",
                    "Beef biltong kevin bacon ribeye t-bone short loin drumstick brisket shankle shank.",
                    "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB"
                )

            )
        )

        //Creating the RecyclerView Adapter
        val adapter = object : GenericRecyclerViewAdapter<Any>(productlist, requireContext()) {

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {

                return ViewHolderFactory.bindView(view, viewType)
            }

            override fun getLayoutId(): Int {
                return R.layout.product_item_layout
            }

            override fun getOnClickLayout(): (List<Any>) -> Unit {
                return {
                    this@CatalogueFragment.findNavController()
                        .navigate(R.id.action_catalogueFragment_to_productFragment)
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


