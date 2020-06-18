package com.cccm.crowingrooster.screens.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.databinding.FragmentChatLogBinding

class ChatLogFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind= DataBindingUtil.inflate<FragmentChatLogBinding>(inflater,
            R.layout.fragment_chat_log,
            container,false)

        bind.recyclerViewChat








        return bind.root
    }

    //funciones de chat

}