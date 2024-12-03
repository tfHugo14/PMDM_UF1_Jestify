package com.example.pmdm_uf1_jestify_2.ui.joke

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class JokeViewModel : ViewModel() {
    private val _jokeType = MutableLiveData<String>()
    private val _jokeContent = MutableLiveData<String>()

    val jokeContent: LiveData<String> get() = _jokeContent
    val jokeType: LiveData<String> get() = _jokeType

    fun setJokeType(jokeType: String) {
        _jokeType.value = jokeType
    }

    fun setJokeContent(jokeContent: String) {
        _jokeContent.value = jokeContent
/*            "What's the difference between an apple and a black guy?\n" +
            "\nThe apple will eventually fall from the tree that it's hanging from!"*/
    }

}