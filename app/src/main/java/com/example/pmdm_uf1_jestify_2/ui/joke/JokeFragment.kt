package com.example.pmdm_uf1_jestify_2.ui.joke

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.pmdm_uf1_jestify_2.R
import com.example.pmdm_uf1_jestify_2.databinding.FragmentJokeBinding
import com.example.pmdm_uf1_jestify_2.jokeAPI.Joke
import com.example.pmdm_uf1_jestify_2.jokeAPI.JokeDAO
import com.example.pmdm_uf1_jestify_2.ui.create.CreateViewModel

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

        // Initialize JokeViewModel
        val jokeDAO = JokeDAO()
        // We use jokeViewModelFactory to pass the JokeDAO instance to the JokeViewModel
        val jokeViewModel = ViewModelProvider(this, JokeViewModelFactory(jokeDAO)).get(JokeViewModel::class.java)
        // Get the CreateViewModel so that we can add joke's to favourites
        val createViewModel = ViewModelProvider(requireActivity()).get(CreateViewModel::class.java)

        // Retrieve argument from navigation bundle
        val jokeType = arguments?.let { JokeFragmentArgs.fromBundle(it).jokeType }
        jokeType?.let { jokeViewModel.setJokeType(it) }

        // Observe jokeType and jokeContent
        jokeViewModel.jokeType.observe(viewLifecycleOwner) { jokeType -> binding.textJoke.text = jokeType }
        jokeViewModel.jokeContent.observe(viewLifecycleOwner) { jokeContent -> binding.contentJoke.text = jokeContent }

        // Bookmark and star rating logic
        val btnSetBookmark: ImageButton = root.findViewById(R.id.btn_set_bookmark)
        btnSetBookmark.setOnClickListener {
            btnSetBookmark.isSelected = !btnSetBookmark.isSelected

            if (btnSetBookmark.isSelected) { // select and deselect bookmark button and add joke to Favourites
                val lastJoke = jokeViewModel.lastFetchedJoke.value
                if (lastJoke != null) {
                    val bookmarkedJoke = Joke(
                        error = lastJoke.error,
                        category = lastJoke.category,
                        type = lastJoke.type,
                        joke = lastJoke.joke,
                        setUp = lastJoke.setUp,
                        delivery = lastJoke.delivery,
                        flags = lastJoke.flags,
                        id = lastJoke.id ?: 9999,
                        safe = lastJoke.safe,
                        lang = lastJoke.lang
                    )
                    createViewModel.addJoke(bookmarkedJoke) // Save joke to shared ViewModel
                    Toast.makeText(requireContext(), "Joke bookmarked!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "No joke to bookmark!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        val starList = listOf(
            root.findViewById<ImageButton>(R.id.btn_set_stars_1),
            root.findViewById<ImageButton>(R.id.btn_set_stars_2),
            root.findViewById<ImageButton>(R.id.btn_set_stars_3),
            root.findViewById<ImageButton>(R.id.btn_set_stars_4),
            root.findViewById<ImageButton>(R.id.btn_set_stars_5)
        )
        starList.forEachIndexed { index, button ->
            button.setOnClickListener {
                for (i in 0..index) starList[i].isSelected = true // select from leftmost to clicked star
                for (i in index + 1 until starList.size) starList[i].isSelected = false // deselect from clicked star to rightmost
            }
        }

        // New Joke Button
        val btnCreateJoke: Button = root.findViewById(R.id.btn_create_joke)
        btnCreateJoke.setOnClickListener {
            // Reset bookmark and star rating
            if (btnSetBookmark.isSelected) { btnSetBookmark.isSelected = false }
            for (i in 0 until starList.size) starList[i].isSelected = false

            // Fetch new joke
            jokeType?.let {
                jokeViewModel.fetchJokeContent(it)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}