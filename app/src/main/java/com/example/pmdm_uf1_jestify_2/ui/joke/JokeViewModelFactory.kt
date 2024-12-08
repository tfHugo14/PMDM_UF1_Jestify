package com.example.pmdm_uf1_jestify_2.ui.joke

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pmdm_uf1_jestify_2.jokeAPI.JokeDAO

class JokeViewModelFactory(private val jokeDAO: JokeDAO) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JokeViewModel::class.java)) {
            return JokeViewModel(jokeDAO) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}