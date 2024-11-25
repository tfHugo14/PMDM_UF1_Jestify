package com.example.pmdm_uf1_jestify.ui.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is to create jokes"
    }
    val text: LiveData<String> = _text
}