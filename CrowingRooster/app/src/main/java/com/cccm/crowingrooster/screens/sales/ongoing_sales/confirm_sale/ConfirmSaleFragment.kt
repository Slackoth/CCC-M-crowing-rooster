package com.cccm.crowingrooster.screens.sales.ongoing_sales.confirm_sale

import android.app.Application
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.cccm.crowingrooster.MainActivity
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.databinding.FragmentConfirmSaleBinding
import com.cccm.crowingrooster.network.body.ConfirmSaleBody
import com.cccm.crowingrooster.network.repository.seller.ConfirmSaleRepository
import com.cccm.crowingrooster.screens.sales.ongoing_sales.ongoing_sale_details.OngoingSalesDetailsFragment
import com.cccm.crowingrooster.screens.sales.ongoing_sales.ongoing_sale_details.OngoingSalesDetailsFragmentDirections
import java.lang.Exception
import java.util.*

class ConfirmSaleFragment: DialogFragment() {

    lateinit var bind: FragmentConfirmSaleBinding
//    lateinit var spinner: Spinner
//    lateinit var confirmTextView: TextView
//    lateinit var cancelTextView: TextView
//    lateinit var timeEt: EditText
//    lateinit var dateEt: EditText
    val calendar: Calendar = Calendar.getInstance()
    private lateinit var viewModel: ConfirmSaleViewModel
    private lateinit var viewModelFactory: ConfirmSaleViewModelFactory
    private lateinit var confirmSaleRepository: ConfirmSaleRepository
    private lateinit var app: Application
    private var saleId: String? = ""
    private var sellerCode: String? = ""
    private var flag: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater,
            R.layout.fragment_confirm_sale, container,false)

        bind.apply {
            hourEt.inputType = InputType.TYPE_NULL
            dateEtt.inputType = InputType.TYPE_NULL
        }

        arguments?.apply {
            saleId = getString("saleId")
            sellerCode = getString("sellerCode")
        }

        Log.d("confirm","$saleId $sellerCode")

        app = requireActivity().application
        confirmSaleRepository = ConfirmSaleRepository.getInstance()
        viewModelFactory = ConfirmSaleViewModelFactory(confirmSaleRepository,saleId,app)
        viewModel = ViewModelProvider(this,viewModelFactory).get(ConfirmSaleViewModel::class.java)

        ArrayAdapter.createFromResource(requireContext(),
            R.array.payment_array,
            android.R.layout.simple_spinner_item).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            bind.paymentSp.adapter = it
        }

        bind.cancelTv.setOnClickListener{
            dismiss()
        }
        bind.confirmTv.setOnClickListener{
            confirm()
            dialog?.dismiss()
        }

        bind.hourEt.setOnClickListener {

            var hour = calendar.get(Calendar.HOUR_OF_DAY)
            var minutes = calendar.get(Calendar.MINUTE)
            var timeListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                bind.hourEt.setText("$hourOfDay:$minute")
            }
            TimePickerDialog(requireContext(),timeListener,hour,minutes,true).show()
        }
        bind.dateEtt.setOnClickListener {
            var day = calendar.get(Calendar.DAY_OF_MONTH)
            var month = calendar.get(Calendar.MONTH)
            var year = calendar.get(Calendar.YEAR)
            var dateListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                bind.dateEtt.setText("$dayOfMonth/$month/$year")
            }
            DatePickerDialog(requireContext(),dateListener,year,month,day).show()

        }

        return bind.root
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        (parentFragment as DialogInterface.OnDismissListener).onDismiss(dialog)
    }

    private fun confirm() {
        viewModel.confirmSale(
            ConfirmSaleBody(
                bind.priceEt.text.toString().toDouble(),
                bind.dateEtt.text.toString(),
                bind.hourEt.text.toString(),
                bind.addressEt.text.toString(),
                bind.paymentSp.selectedItem.toString()
            )
        )
    }
}