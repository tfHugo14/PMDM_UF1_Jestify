package com.example.pmdm_uf1_jestify_2.ui.joke

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pmdm_uf1_jestify_2.jokeAPI.Joke
import com.example.pmdm_uf1_jestify_2.jokeAPI.JokeDAO
import kotlinx.coroutines.launch

class JokeViewModel(private val jokeDAO: JokeDAO) : ViewModel() {
    private val _jokeType = MutableLiveData<String>()
    private val _jokeContent = MutableLiveData<String>()
    private val _lastFetchedJoke = MutableLiveData<Joke?>() // Store the last fetched Joke

    val jokeContent: LiveData<String> get() = _jokeContent
    val jokeType: LiveData<String> get() = _jokeType
    val lastFetchedJoke: LiveData<Joke?> get() = _lastFetchedJoke

    fun setJokeType(jokeType: String) {
        _jokeType.value = jokeType
        fetchJokeContent(jokeType) // Fetch the joke when jokeType is set
    }

    fun fetchJokeContent(jokeType: String) {
        viewModelScope.launch {
            try {
                val joke = jokeDAO.getJokeByCategory(jokeType) // Call suspend function
                _lastFetchedJoke.postValue(joke) // Save the last fetched joke

                var jokeContent = joke?.joke ?: "No joke available"

                if (joke?.setUp != null && joke.delivery != null)
                    jokeContent = (joke.setUp + "\n\n" + joke.delivery)

                if (joke?.id != null)
                    jokeContent = "Nº - " + joke.id.toString() + "\n\n" + jokeContent

                _jokeContent.postValue(jokeContent) // Update LiveData with fetched joke
            } catch (e: Exception) {
                e.printStackTrace()
                _jokeContent.postValue("Failed to load joke")
                _lastFetchedJoke.postValue(null)
            }
        }
    }
}