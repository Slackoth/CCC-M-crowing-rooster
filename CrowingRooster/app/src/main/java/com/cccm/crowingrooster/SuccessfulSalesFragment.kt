package com.cccm.crowingrooster

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cccm.crowingrooster.databinding.FragmentSuccessfulSalesBinding

/**
 * A simple [Fragment] subclass.
 */
class SuccessfulSalesFragment : Fragment() {

    companion object {
        const val TAG: String = "SuccessfulSalesFragment"
    }

    var mName: MutableList<String> = mutableListOf()
    var mImgUrl: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_successful_sales, container, false)
        val bind = DataBindingUtil.inflate<FragmentSuccessfulSalesBinding>(inflater,R.layout.fragment_successful_sales,
        container, false)

        Log.d(TAG, "onCreate: Started")

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initImageBitmap()
    }

    private fun initImageBitmap() {
        Log.d(TAG, "onCreate: preparing bitmaps")
        var name: Collection<String> = listOf("Bojack","Mr. Peanutbutter", "Princess Carolyn", "Sarah Lynn",
            "Herb Kazzaz", "Secretariat", "Beatrice Horseman")
        var url: Collection<String> = listOf("https://m.media-amazon.com/images/M/MV5BYWQwMDNkM2MtODU4OS00OTY3LTgwOTItNjE2Yzc0MzRkMDllXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_SY1000_CR0,0,675,1000_AL_.jpg",
        "https://66.media.tumblr.com/caec5f9863f1bdaa504381fab52cd1db/tumblr_pqhd86XMep1vfecm0o3_540.png",
        "https://dotandline.net/wp-content/uploads/2017/09/1_W1Zpv8RMX9oSgZmiMGb-OQ-1210x642.jpeg",
        "https://i.pinimg.com/564x/83/7f/f5/837ff5293c96e0db71204a3c12c87299.jpg",
        "https://vignette.wikia.nocookie.net/bojackhorseman/images/e/e9/Herb_Kazazz_80s.png/revision/latest/scale-to-width-down/310?cb=20181013183030",
            "https://vignette.wikia.nocookie.net/bojackhorseman/images/a/a6/Secretariat.png/revision/latest/scale-to-width-down/310?cb=20150721234038",
        "https://vignette.wikia.nocookie.net/bojackhorseman/images/0/0a/Beatrice_funeral_outfit.jpeg/revision/latest/scale-to-width-down/310?cb=20171025225257")

        mName.addAll(name)
        mImgUrl.addAll(url)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        Log.d(TAG,"initRecyclerView: initialization")

        var recyclerView: RecyclerView = requireView().findViewById(R.id.recycler_view)
        var adapter = RecyclerViewAdapter(requireContext(), mName,mImgUrl)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}
