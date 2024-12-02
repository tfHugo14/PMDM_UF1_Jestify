package com.example.pmdm_uf1_jestify_2.ui.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavouritesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is the favourites section"
    }
    val text: LiveData<String> = _text
}