package com.example.pmdm_uf1_jestify_2.ui.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pmdm_uf1_jestify_2.jokeAPI.Joke

class CreateViewModel : ViewModel() {

    private val _jokes = MutableLiveData<MutableList<Joke>>(mutableListOf())
    val jokes: LiveData<MutableList<Joke>> get() = _jokes

    fun addJoke(joke: Joke) {
        val currentList = _jokes.value ?: mutableListOf()
        currentList.add(joke)
        _jokes.value = currentList
    }

    fun removeJoke(joke: Joke) {
        val currentJokes = _jokes.value ?: mutableListOf()
        currentJokes.remove(joke)
        _jokes.value = currentJokes
    }
}
