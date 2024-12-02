package com.example.pmdm_uf1_jestify_2.ui.joke

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.pmdm_uf1_jestify_2.R
import com.example.pmdm_uf1_jestify_2.databinding.FragmentJokeBinding
import com.example.pmdm_uf1_jestify_2.ui.favourites.FavouritesViewModel

class JokeFragment : Fragment() {

    private var _binding: FragmentJokeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJokeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // initialize JokeViewModel
        val jokeViewModel = ViewModelProvider(this).get(JokeViewModel::class.java)

        // retrive argument
        val jokeType = arguments?.let {
            JokeFragmentArgs.fromBundle(it).jokeType
        }

        // send jokeType to JokeViewModel
        jokeType?.let { jokeViewModel.setJokeType(it) }

        // observe LiveData text to update the textView
        jokeViewModel.jokeType.observe(viewLifecycleOwner) {
            jokeType -> binding.textJoke.text = jokeType
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}