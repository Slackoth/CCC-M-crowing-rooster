package com.cccm.crowingrooster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.cccm.crowingrooster.databinding.FragmentConcludingOrderBinding

class ConcludingOrderFragment: DialogFragment() {
    lateinit var bind: FragmentConcludingOrderBinding
    lateinit var cancelTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_concluding_order, container, false)

        bind.apply {
            cancelTextView = cancelConcludingOrderTv
        }

        cancelTextView.setOnClickListener {
            dialog?.dismiss()
        }

        return bind.root
    }
}