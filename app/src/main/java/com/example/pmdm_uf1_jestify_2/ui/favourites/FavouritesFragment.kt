package com.example.pmdm_uf1_jestify_2.ui.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pmdm_uf1_jestify_2.R
import com.example.pmdm_uf1_jestify_2.ui.create.CreateViewModel

class FavouritesFragment : Fragment() {

    private lateinit var favouritesViewModel: CreateViewModel
    private lateinit var favouritesRecycleViewAdapter: FavouritesRecycleViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_favourites, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.jokesRecyclerView)

        favouritesViewModel = ViewModelProvider(requireActivity()).get(CreateViewModel::class.java)

        favouritesViewModel.jokes.observe(viewLifecycleOwner) { jokes ->
            favouritesRecycleViewAdapter = FavouritesRecycleViewAdapter(jokes.toMutableList()) { jokeToDelete ->
                // Remove joke from LiveData
                favouritesViewModel.removeJoke(jokeToDelete)
                // Update the adapter
                favouritesRecycleViewAdapter.removeJoke(jokeToDelete)
            }
            recyclerView.adapter = favouritesRecycleViewAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }

        return root
    }
}