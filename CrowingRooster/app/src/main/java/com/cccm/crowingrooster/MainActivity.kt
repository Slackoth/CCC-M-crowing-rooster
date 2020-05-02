package com.cccm.crowingrooster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.cccm.crowingrooster.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //DataBinding
        val bind = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }
}
