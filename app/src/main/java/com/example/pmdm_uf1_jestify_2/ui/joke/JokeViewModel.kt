package com.example.pmdm_uf1_jestify_2.ui.joke

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class JokeViewModel : ViewModel() {
    private val _jokeType = MutableLiveData<String>()

    val jokeType: LiveData<String> get() = _jokeType

    fun setJokeType(jokeType: String) {
        _jokeType.value = "Category: $jokeType"
    }

}