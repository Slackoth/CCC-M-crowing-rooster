package com.cccm.crowingrooster.screens.seller_profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SellerProfileViewModel: ViewModel() {
    private var _code: MutableLiveData<String> = MutableLiveData()
    val code: LiveData<String>
        get() = _code

    private var _name: MutableLiveData<String> = MutableLiveData()
    val name: LiveData<String>
        get() = _name

    private var _phone: MutableLiveData<String> = MutableLiveData()
    val phone: LiveData<String>
        get() = _phone

    private var _email: MutableLiveData<String> = MutableLiveData()
    val email: LiveData<String>
        get() = _email

    init {
        _code.value = "00082318"
        _name.value = "Mr. Peanutbutter"
        _phone.value = "6787-0786"
        _email.value = "ungallocontenis@gmail.com"
    }

    fun editName() {
        _name.value = "Princess Carolyn"
    }
    fun editPhone() {
        _phone.value = "6878-0876"
    }
    fun editEmail() {
        _email.value = "nolosebroperomira@gmail.com"
    }

}