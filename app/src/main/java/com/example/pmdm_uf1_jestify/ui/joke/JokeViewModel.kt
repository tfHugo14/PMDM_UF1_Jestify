package com.example.pmdm_uf1_jestify.ui.joke

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class JokeViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is the joke section"
    }
    val text: LiveData<String> = _text
}