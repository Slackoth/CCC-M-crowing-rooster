package com.cccm.crowingrooster

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.graphics.drawable.ClipDrawable.VERTICAL
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cccm.crowingrooster.databinding.FragmentSuccessfulSalesBinding
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass.
 */
class SuccessfulSalesFragment : Fragment(), RecyclerViewAdapter.OnItemClickListener {

    companion object {
        const val TAG: String = "SuccessfulSalesFragment"
    }

    lateinit var context: FragmentActivity
    var clientName: MutableList<String> = mutableListOf()
    var modelName: MutableList<String> = mutableListOf()
    var quantityNum: MutableList<Int> = mutableListOf()
    var imgUrl: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_successful_sales, container, false)
        val bind = DataBindingUtil.inflate<FragmentSuccessfulSalesBinding>(inflater,R.layout.fragment_successful_sales,
        container, false)

        Log.d(TAG, "onCreate: Started")
        (activity as MainActivity).supportActionBar?.title = getString(R.string.successful_sales)

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initImageBitmap()
    }

    private fun initImageBitmap() {
        Log.d(TAG, "onCreate: preparing bitmaps")
        val client = listOf<String>("JOSE LUIS ALBARRAN REQUENA","MIGUEL MATESANZ ARTIGAS",
        "FELIX BARRIO RECIO","LUCIA LLORCA CAÑO","ANTONIA AGUERA PAN","CONCEPCION IBAÑEZ POSADA")
        val model = listOf<String>("22F-30","42-330","42R-330","24-350","24R-350","27-540")
        val quantity = listOf<Int>(1,20,300,4000,50000,6000000)
        var url = listOf("https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB",
        "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB",
        "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB",
        "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB",
        "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB",
        "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB")

        clientName.addAll(client)
        modelName.addAll(model)
        quantityNum.addAll(quantity)
        imgUrl.addAll(url)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        Log.d(TAG,"initRecyclerView: initialization")

        var recyclerView: RecyclerView = requireView().findViewById(R.id.recycler_view)
        var orientation = resources.configuration.orientation

//        TODO: In case someday we need to change the orientation of the RecyclerView. DO NOT DELETE IT
//        recyclerView.layoutManager = LinearLayoutManager(requireContext(),
//            if (orientation == Configuration.ORIENTATION_LANDSCAPE) LinearLayoutManager.HORIZONTAL
//            else LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(),R.drawable.recyclerview_divider))

        var adapter = RecyclerViewAdapter(requireContext(), clientName,imgUrl,modelName,quantityNum,this)
        recyclerView.adapter = adapter

    }

    override fun onItemClickListener(view: View) {
        val dialog = SaleDetailsDialogFragment()
        context = activity as FragmentActivity
        dialog.show(context.supportFragmentManager, "Dialog")
    }

}
